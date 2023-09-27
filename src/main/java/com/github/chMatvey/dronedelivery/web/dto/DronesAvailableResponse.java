package com.github.chMatvey.dronedelivery.web.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DronesAvailableResponse(
        @NotNull
        List<@NotNull DroneResponse> availableDrones,

        @NotNull
        int count
) {
}
