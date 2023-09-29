package com.github.chMatvey.dronedelivery.service.impl;

import com.github.chMatvey.dronedelivery.model.Delivery;
import com.github.chMatvey.dronedelivery.model.Drone;
import com.github.chMatvey.dronedelivery.model.Medication;
import com.github.chMatvey.dronedelivery.repository.DeliveryRepository;
import com.github.chMatvey.dronedelivery.repository.DroneRepository;
import com.github.chMatvey.dronedelivery.service.data.DeliveryCreation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.github.chMatvey.dronedelivery.model.DroneMother.droneInIdleState;
import static com.github.chMatvey.dronedelivery.model.DroneState.LOADING;
import static com.github.chMatvey.dronedelivery.model.MedicationMother.aspirin;
import static com.github.chMatvey.dronedelivery.model.MedicationMother.ibuprofen;
import static com.github.chMatvey.dronedelivery.web.request.MedicationsLoadRequestMother.medications;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DeliveryServiceImpl.class)
class DeliveryServiceImplTest {
    @MockBean
    DroneRepository droneRepository;

    @MockBean
    DeliveryRepository deliveryRepository;

    @Autowired
    DeliveryServiceImpl deliveryService;

    @Test
    void createDelivery() {
        // Given
        Drone drone = droneInIdleState();
        List<Medication> medications = List.of(aspirin(), aspirin(), ibuprofen());
        int availableWeightCapacity = drone.getWeightLimit() - medications.stream().mapToInt(Medication::getWeight).sum();

        DeliveryCreation deliveryCreation = DeliveryCreation.builder()
                .drone(drone)
                .medications(medications)
                .availableWeightCapacity(availableWeightCapacity)
                .build();

        doAnswer(returnsFirstArg()).when(droneRepository).save(Mockito.any(Drone.class));
        doAnswer(returnsFirstArg()).when(deliveryRepository).save(Mockito.any(Delivery.class));

        // When
        Delivery delivery = deliveryService.createDelivery(deliveryCreation);

        // Then
        assertEquals(drone, delivery.getDrone());
        assertEquals(medications, delivery.getMedications());
        assertEquals(availableWeightCapacity, delivery.getAvailableWeightCapacity());
        assertEquals(LOADING, drone.getState());
        assertEquals(2, delivery.getDeliveryItems().size());

        verify(droneRepository).save(drone);
        verify(deliveryRepository).save(delivery);
    }
}