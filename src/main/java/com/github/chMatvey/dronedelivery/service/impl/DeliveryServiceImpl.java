package com.github.chMatvey.dronedelivery.service.impl;

import com.github.chMatvey.dronedelivery.model.Delivery;
import com.github.chMatvey.dronedelivery.model.Drone;
import com.github.chMatvey.dronedelivery.model.Medication;
import com.github.chMatvey.dronedelivery.repository.DeliveryRepository;
import com.github.chMatvey.dronedelivery.repository.DroneRepository;
import com.github.chMatvey.dronedelivery.service.DeliveryService;
import com.github.chMatvey.dronedelivery.service.data.DeliveryCreation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.github.chMatvey.dronedelivery.model.DroneState.LOADING;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DroneRepository droneRepository;
    private final DeliveryRepository deliveryRepository;

    @Transactional
    @Override
    public Delivery createDelivery(DeliveryCreation deliveryCreation) {
        Drone drone = droneRepository.save(deliveryCreation.drone().setState(LOADING));

        Delivery delivery = Delivery.builder()
                .availableWeightCapacity(deliveryCreation.availableWeightCapacity())
                .completed(false)
                .drone(drone)
                .medications(deliveryCreation.medications())
                .build();

        return deliveryRepository.save(delivery);
    }

    @Override
    public Optional<Delivery> findDelivery(Drone drone) {
        return deliveryRepository.findOneByDroneAndCompletedFalse(drone);
    }

    @Override
    public Optional<List<Medication>> findDeliveryMedications(Drone drone) {
        return deliveryRepository.findOneByDroneAndCompletedFalseJoinMedications(drone)
                .map(Delivery::getMedications);
    }
}
