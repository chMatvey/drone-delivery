package com.github.chMatvey.dronedelivery.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MedicationsLoadRequest(
        @NotBlank
        List<@NotNull Integer> medicalItemsIds
) {
}
