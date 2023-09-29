package com.github.chMatvey.dronedelivery.web.response;

import jakarta.validation.constraints.NotNull;

public record MedicationsLoadResponse(
        @NotNull
        int deliveryId
) {
}
