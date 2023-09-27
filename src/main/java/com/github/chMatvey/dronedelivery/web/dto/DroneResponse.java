package com.github.chMatvey.dronedelivery.web.dto;

import com.github.chMatvey.dronedelivery.model.DroneModel;
import com.github.chMatvey.dronedelivery.model.DroneState;
import jakarta.validation.constraints.*;

public record DroneResponse(
        @NotNull
        int id,

        @NotBlank
        String serialNumber,

        @NotNull
        DroneModel model,

        @Max(500)
        @Positive
        @NotNull
        int weightLimit,

        @PositiveOrZero
        @NotNull
        int batteryCapacity,

        @NotNull
        DroneState state
) {
}
