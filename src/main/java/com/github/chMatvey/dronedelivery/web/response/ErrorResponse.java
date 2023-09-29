package com.github.chMatvey.dronedelivery.web.response;

import jakarta.validation.constraints.NotNull;

public record ErrorResponse(@NotNull String message) {
}
