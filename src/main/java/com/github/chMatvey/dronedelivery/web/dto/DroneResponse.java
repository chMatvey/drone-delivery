package com.github.chMatvey.dronedelivery.web.dto;

import com.github.chMatvey.dronedelivery.model.DroneModel;
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
        int batteryCapacity
) {
}
