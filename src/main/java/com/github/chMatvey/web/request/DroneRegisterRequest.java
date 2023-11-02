package com.github.chMatvey.web.request;

import com.github.chMatvey.model.DroneModel;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record DroneRegisterRequest(
        @NotBlank
        @Size(max = 100)
        String serialNumber,

        @NotNull
        DroneModel model,

        @Max(500)
        @Positive
        @NotNull
        int weightLimit
) {
}
