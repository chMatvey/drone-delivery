package com.github.chMatvey.dronedelivery.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DroneBatteryLevelResponse(
        @PositiveOrZero
        @NotNull
        int batteryLevel
) {
}
