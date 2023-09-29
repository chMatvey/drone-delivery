package com.github.chMatvey.dronedelivery.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record MedicationFullUpdateRequest(
        @Schema(example = "Lipitor")
        @Pattern(regexp = "^[\\w-]+$")
        @NotNull
        String name,

        @Schema(example = "100")
        @NotNull
        @Positive
        int weight,

        @Pattern(regexp = "^[A-Z0-9_-]+$")
        @NotNull
        String code
) {
}
