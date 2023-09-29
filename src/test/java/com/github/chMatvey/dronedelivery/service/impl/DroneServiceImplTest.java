package com.github.chMatvey.dronedelivery.service.impl;

import com.github.chMatvey.dronedelivery.model.Delivery;
import com.github.chMatvey.dronedelivery.model.Drone;
import com.github.chMatvey.dronedelivery.model.Medication;
import com.github.chMatvey.dronedelivery.repository.DroneRepository;
import com.github.chMatvey.dronedelivery.repository.MedicationRepository;
import com.github.chMatvey.dronedelivery.service.DeliveryService;
import com.github.chMatvey.dronedelivery.service.data.DeliveryCreation;
import com.github.chMatvey.dronedelivery.service.exception.DroneBatteryLevelTooLowException;
import com.github.chMatvey.dronedelivery.service.exception.DroneNotFoundException;
import com.github.chMatvey.dronedelivery.service.exception.MedicationsWeightOverTheLimitException;
import com.github.chMatvey.dronedelivery.web.request.DroneRegistrationRequest;
import com.github.chMatvey.dronedelivery.web.request.MedicationsLoadRequest;
import com.github.chMatvey.dronedelivery.web.response.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.github.chMatvey.dronedelivery.model.DroneMother.droneInIdleState;
import static com.github.chMatvey.dronedelivery.model.DroneMother.droneInLoadedState;
import static com.github.chMatvey.dronedelivery.web.request.DroneRegistrationRequestMother.droneRegistrationRequest;
import static com.github.chMatvey.dronedelivery.web.request.MedicationsLoadRequestMother.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DroneServiceImpl.class)
class DroneServiceImplTest {
    @MockBean
    DroneRepository droneRepository;

    @MockBean
    MedicationRepository medicationRepository;

    @MockBean
    DeliveryService deliveryService;

    @Autowired
    DroneServiceImpl droneService;

    @DisplayName("Should register drone successfully")
    @Test
    void registerDrone() {
        // Given
        DroneRegistrationRequest request = droneRegistrationRequest();
        doAnswer(returnsFirstArg()).when(droneRepository).save(any(Drone.class));

        // When
        DroneRegistrationResponse response = droneService.registerDrone(request);

        // Then
        assertNotNull(response);
        verify(droneRepository).save(any(Drone.class));
    }

    @DisplayName("Should load medications successfully")
    @Test
    void loadMedicationItems() {
        // Given
        MedicationsLoadRequest request = medicationsLoadRequest();

        Drone drone = droneInIdleState();
        int deliveryId = 11;
        Delivery deliveryMock = mock(Delivery.class);
        List<Medication> medications = medications();

        when(droneRepository.findById(drone.getId())).thenReturn(Optional.of(drone));
        when(medicationRepository.findAllById(request.medicationsIds())).thenReturn(medications);
        when(deliveryService.createDelivery(any(DeliveryCreation.class))).thenReturn(deliveryMock);
        when(deliveryMock.getId()).thenReturn(deliveryId);

        // When
        MedicationsLoadResponse response = droneService.loadMedicationItems(drone.getId(), request);

        // Then
        assertEquals(deliveryId, response.deliveryId());
        verify(deliveryService).createDelivery(DeliveryCreation.builder()
                .drone(drone)
                .medications(List.of(medications.get(0), medications.get(0), medications.get(1), medications.get(2)))
                .availableWeightCapacity(70)
                .build());
    }

    @DisplayName("Should failed on loading medication because medications weight more that drone weight limit")
    @Test
    void shouldFailWhenMedicationsOverweight() {
        // Given
        MedicationsLoadRequest request = medicationsLoadRequestWithOverweight();

        Drone drone = droneInIdleState();
        List<Medication> medications = medications();

        when(droneRepository.findById(drone.getId())).thenReturn(Optional.of(drone));
        when(medicationRepository.findAllById(request.medicationsIds())).thenReturn(medications);

        // Then
        assertThrows(MedicationsWeightOverTheLimitException.class, () -> droneService.loadMedicationItems(drone.getId(), request));
    }

    @Test
    void shouldFailLoadMedicationWhenBatteryLevelBelowMinNumber() {
        // Given
        MedicationsLoadRequest request = medicationsLoadRequest();
        Drone drone = droneInIdleState().setBatteryCapacity(20);

        when(droneRepository.findById(drone.getId())).thenReturn(Optional.of(drone));

        // Then
        assertThrows(DroneBatteryLevelTooLowException.class, () -> droneService.loadMedicationItems(drone.getId(), request));
    }

    @DisplayName("Should successfully get loaded medications")
    @Test
    void getLoadedMedication() {
        // Given
        Drone drone = droneInLoadedState();
        List<Medication> medications = medications();

        when(droneRepository.findById(drone.getId())).thenReturn(Optional.of(drone));
        when(deliveryService.findDeliveryMedications(drone)).thenReturn(Optional.of(medications));

        // When
        MedicationsLoadedResponse response = droneService.getLoadedMedication(drone.getId());

        // Then
        assertEquals(medications.size(), response.count());
        assertEquals(medications.stream().map(MedicationResponse::fromModel).toList(), response.medications());
    }

    @DisplayName("Should return empty medication list if drone in IDLE state")
    @Test
    void shouldReturnEmptyListWhenDroneInIdleState() {
        // Given
        Drone drone = droneInIdleState();
        when(droneRepository.findById(drone.getId())).thenReturn(Optional.of(drone));

        // When
        MedicationsLoadedResponse response = droneService.getLoadedMedication(drone.getId());

        // Then
        assertEquals(0, response.count());
        assertTrue(response.medications().isEmpty());
    }

    @DisplayName("Should return drone battery level")
    @Test
    void getDroneBatteryLevel() {
        // Given
        Drone drone = droneInIdleState();
        when(droneRepository.findById(drone.getId())).thenReturn(Optional.of(drone));

        // When
        DroneBatteryLevelResponse droneBatteryLevel = droneService.getDroneBatteryLevel(drone.getId());

        // Then
        assertEquals(drone.getBatteryCapacity(), droneBatteryLevel.batteryLevel());
    }

    @DisplayName("Should failed when drone not existed")
    @Test
    void getDroneBatteryLevelNotFound() {
        // Given
        int notExistedDroneId = 12;
        when(droneRepository.findById(notExistedDroneId)).thenReturn(Optional.empty());

        // Then
        assertThrows(DroneNotFoundException.class, () -> droneService.getDroneBatteryLevel(notExistedDroneId));
    }
}