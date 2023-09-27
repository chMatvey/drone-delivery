package com.github.chMatvey.dronedelivery.web.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MedicationsLoadedResponse(
        @NotNull
        List<@NotNull MedicationResponse> medications,

        @NotNull
        int count
) {
}
