package com.github.chMatvey.dronedelivery.web.dto;

import com.github.chMatvey.dronedelivery.model.Medication;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record MedicationResponse(
        @NotNull
        int id,

        @NotBlank
        String name,

        @Positive
        @NotNull
        int weight,

        @NotBlank
        String code
) {
        public static MedicationResponse fromModel(Medication model) {
                return MedicationResponse.builder()
                        .id(model.getId())
                        .name(model.getName())
                        .weight(model.getWeight())
                        .code(model.getCode())
                        .build();
        }
}
