package com.github.chMatvey.dronedelivery.service.data;

import com.github.chMatvey.dronedelivery.model.Drone;
import com.github.chMatvey.dronedelivery.model.Medication;
import lombok.Builder;

import java.util.List;

@Builder
public record DeliveryCreation(
        Drone drone,
        List<Medication> medications,
        int availableWeightCapacity
) {
}
