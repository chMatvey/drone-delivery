package com.github.chMatvey.dronedelivery.model;

import lombok.experimental.UtilityClass;

import static com.github.chMatvey.dronedelivery.model.DroneModel.CRUISERWEIGHT;
import static com.github.chMatvey.dronedelivery.model.DroneModel.HEAVYWEIGHT;
import static com.github.chMatvey.dronedelivery.model.DroneState.IDLE;
import static com.github.chMatvey.dronedelivery.model.DroneState.LOADED;
import static java.time.LocalDateTime.now;

@UtilityClass
public class DroneMother {
    public static Drone droneInIdleState() {
        return Drone.builder()
                .id(1)
                .serialNumber("DRN54321")
                .model(HEAVYWEIGHT)
                .weightLimit(500)
                .batteryCapacity(90)
                .state(IDLE)
                .createdAt(now())
                .build();
    }

    public static Drone droneInLoadedState() {
        return Drone.builder()
                .id(2)
                .serialNumber("DRN98765")
                .model(CRUISERWEIGHT)
                .weightLimit(400)
                .batteryCapacity(90)
                .state(LOADED)
                .createdAt(now())
                .build();
    }
}