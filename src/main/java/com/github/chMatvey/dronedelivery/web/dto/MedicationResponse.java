package com.github.chMatvey.dronedelivery.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record MedicationResponse(
        @NotBlank
        String name,

        @Positive
        int weight,

        @NotBlank
        String code
) {
}
