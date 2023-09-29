package com.github.chMatvey.dronedelivery.service.impl;

import com.github.chMatvey.dronedelivery.model.Delivery;
import com.github.chMatvey.dronedelivery.model.Drone;
import com.github.chMatvey.dronedelivery.model.Medication;
import com.github.chMatvey.dronedelivery.repository.DroneRepository;
import com.github.chMatvey.dronedelivery.repository.MedicationRepository;
import com.github.chMatvey.dronedelivery.service.DeliveryService;
import com.github.chMatvey.dronedelivery.service.DroneService;
import com.github.chMatvey.dronedelivery.service.data.DeliveryCreation;
import com.github.chMatvey.dronedelivery.service.exception.DroneNotFoundException;
import com.github.chMatvey.dronedelivery.service.exception.MedicationNotFoundException;
import com.github.chMatvey.dronedelivery.service.exception.MedicationsWeightOverTheLimitException;
import com.github.chMatvey.dronedelivery.service.exception.UnexpectedDroneStatusException;
import com.github.chMatvey.dronedelivery.web.request.DroneRegistrationRequest;
import com.github.chMatvey.dronedelivery.web.request.MedicationsLoadRequest;
import com.github.chMatvey.dronedelivery.web.response.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.github.chMatvey.dronedelivery.model.Constant.MAX_BATTERY_CAPACITY;
import static com.github.chMatvey.dronedelivery.model.Constant.NO_MEDICATIONS_STATES;
import static com.github.chMatvey.dronedelivery.model.DroneState.IDLE;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
@Slf4j
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final DeliveryService deliveryService;

    @Transactional
    @Override
    public DroneRegistrationResponse registerDrone(DroneRegistrationRequest request) {
        Drone drone = Drone.builder()
                .serialNumber(request.serialNumber())
                .model(request.model())
                .weightLimit(request.weightLimit())
                .batteryCapacity(MAX_BATTERY_CAPACITY)
                .state(IDLE)
                .build();

        int id = droneRepository.save(drone).getId();
        return new DroneRegistrationResponse(id);
    }

    @Override
    public MedicationsLoadResponse loadMedicationItems(int droneId, MedicationsLoadRequest request) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(DroneNotFoundException::new);
        if (drone.getState() != IDLE)
            throw new UnexpectedDroneStatusException();

        List<Medication> medications = extractMedications(request.medicationsIds());

        int medicationsWeight = medications.stream()
                .map(Medication::getWeight)
                .mapToInt(Integer::intValue).sum();

        if (medicationsWeight > drone.getWeightLimit()) {
            log.warn("Medication weight = {} more than drone weight limit = {}", medicationsWeight, drone.getWeightLimit());
            throw new MedicationsWeightOverTheLimitException();
        }

        Delivery delivery = deliveryService.createDelivery(DeliveryCreation.builder()
                .drone(drone)
                .medications(medications)
                .availableWeightCapacity(drone.getWeightLimit() - medicationsWeight)
                .build());

        return new MedicationsLoadResponse(delivery.getId());
    }

    @Override
    public MedicationsLoadedResponse getLoadedMedication(int droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(DroneNotFoundException::new);

        if (NO_MEDICATIONS_STATES.contains(drone.getState()))
            return MedicationsLoadedResponse.emptyResponse();

        List<MedicationResponse> medications = deliveryService.findDeliveryMedications(drone)
                .orElseThrow(DroneNotFoundException::new)
                .stream()
                .map(MedicationResponse::fromModel)
                .toList();

        return new MedicationsLoadedResponse(medications, medications.size());
    }

    @Override
    public AvailableDronesResponse getAvailableDrones() {
        List<DroneResponse> availableDrones = droneRepository.findAll().stream()
                .filter(drone -> drone.getState() == IDLE)
                .map(DroneResponse::fromModel)
                .toList();

        return new AvailableDronesResponse(availableDrones, availableDrones.size());
    }

    @Override
    public DroneBatteryLevelResponse getDroneBatteryLevel(int droneId) {
        Integer batteryCapacity = droneRepository.findById(droneId)
                .map(Drone::getBatteryCapacity)
                .orElseThrow(DroneNotFoundException::new);

        return new DroneBatteryLevelResponse(batteryCapacity);
    }

    private List<Medication> extractMedications(List<Integer> medicationsIds) throws MedicationNotFoundException {
        Map<Integer, Medication> idsPerMedication = medicationRepository.findAllById(medicationsIds)
                .stream()
                .collect(Collectors.toMap(Medication::getId, Function.identity()));

        return medicationsIds.stream()
                .map(id -> ofNullable(idsPerMedication.get(id))
                        .orElseThrow(MedicationNotFoundException::new))
                .toList();
    }
}
