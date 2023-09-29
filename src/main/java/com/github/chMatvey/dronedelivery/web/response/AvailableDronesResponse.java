package com.github.chMatvey.dronedelivery.web.response;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AvailableDronesResponse(
        @NotNull
        List<DroneResponse> availableDrones,

        @NotNull
        int count
) {
}
