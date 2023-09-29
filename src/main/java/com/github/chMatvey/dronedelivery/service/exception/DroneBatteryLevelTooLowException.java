package com.github.chMatvey.dronedelivery.service.exception;

import static com.github.chMatvey.dronedelivery.model.Constant.MIN_BATTERY_LEVEL;

public class DroneBatteryLevelTooLowException extends RuntimeException {
    public DroneBatteryLevelTooLowException() {
        super("Drone battery level is below %d percents".formatted(MIN_BATTERY_LEVEL));
    }
}
