package com.github.chMatvey.dronedelivery.web.response;

import jakarta.validation.constraints.NotNull;

public record DroneRegistrationResponse(
        @NotNull
        int id
) {
}
