package com.github.chMatvey.dronedelivery.web.response;

import com.github.chMatvey.dronedelivery.model.Medication;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MedicationResponse(
        @NotNull
        int id,

        @NotNull
        String name,

        @NotNull
        int weight,

        @NotNull
        String code
) {
        public static MedicationResponse fromModel(Medication medication) {
                return MedicationResponse.builder()
                        .id(medication.getId())
                        .name(medication.getName())
                        .weight(medication.getWeight())
                        .code(medication.getCode())
                        .build();
        }
}
