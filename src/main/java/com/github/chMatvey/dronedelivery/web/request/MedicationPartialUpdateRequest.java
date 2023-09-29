package com.github.chMatvey.dronedelivery.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record MedicationPartialUpdateRequest(
        @Schema(example = "Lipitor")
        @Nullable
        @Pattern(regexp = "^[\\w-]+$")
        String name,

        @Schema(example = "100")
        @Nullable
        @Positive
        Integer weight,

        @Nullable
        @Pattern(regexp = "^[A-Z0-9_-]+$")
        String code
) {
}
