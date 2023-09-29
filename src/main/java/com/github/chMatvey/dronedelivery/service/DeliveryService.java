package com.github.chMatvey.dronedelivery.service;

import com.github.chMatvey.dronedelivery.model.Delivery;
import com.github.chMatvey.dronedelivery.model.Drone;
import com.github.chMatvey.dronedelivery.model.Medication;
import com.github.chMatvey.dronedelivery.service.data.DeliveryCreation;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    Delivery createDelivery(DeliveryCreation deliveryCreation);

    Optional<Delivery> findDelivery(Drone drone);

    Optional<List<Medication>> findDeliveryMedications(Drone drone);
}
