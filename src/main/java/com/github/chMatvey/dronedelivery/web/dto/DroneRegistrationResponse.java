package com.github.chMatvey.dronedelivery.web.dto;

import jakarta.validation.constraints.NotNull;

public record DroneRegistrationResponse(
        @NotNull
        int id
) {
}
