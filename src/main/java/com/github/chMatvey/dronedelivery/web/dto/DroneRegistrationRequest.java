package com.github.chMatvey.dronedelivery.web.dto;

import com.github.chMatvey.dronedelivery.model.DroneModel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DroneRegistrationRequest(
        @NotBlank
        String serialNumber,

        @NotNull
        DroneModel model,

        @Max(500)
        @Positive
        @NotNull
        int weightLimit
) {
}
