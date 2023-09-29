package com.github.chMatvey.dronedelivery.web.response;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DroneBatteryLevelResponse(
        @NotNull
        int batteryLevel
) {
}
