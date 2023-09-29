package com.github.chMatvey.dronedelivery.model;

import lombok.experimental.UtilityClass;

import java.util.Set;

import static com.github.chMatvey.dronedelivery.model.DroneState.*;

@UtilityClass
public class Constant {
    public static final int MAX_BATTERY_CAPACITY = 100;

    public static final Set<DroneState> NO_MEDICATIONS_STATES = Set.of(
            IDLE,
            DELIVERED,
            RETURNING
    );
}
