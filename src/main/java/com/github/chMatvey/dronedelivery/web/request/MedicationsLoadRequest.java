package com.github.chMatvey.dronedelivery.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record MedicationsLoadRequest(
        @Schema(description = "Id of medication items. It can contains several identical values.")
        @Size(min = 1, max = 10)
        @NotNull
        List<Integer> medicationsIds
) {
}
