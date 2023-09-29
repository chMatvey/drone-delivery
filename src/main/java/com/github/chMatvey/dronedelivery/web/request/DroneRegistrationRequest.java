package com.github.chMatvey.dronedelivery.web.request;

import com.github.chMatvey.dronedelivery.model.DroneModel;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record DroneRegistrationRequest(
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
