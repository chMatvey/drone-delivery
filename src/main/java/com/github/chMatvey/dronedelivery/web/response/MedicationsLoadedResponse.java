package com.github.chMatvey.dronedelivery.web.response;

import jakarta.validation.constraints.NotNull;

import java.util.List;

import static java.util.Collections.emptyList;

public record MedicationsLoadedResponse(
        @NotNull
        List<MedicationResponse> medications,

        @NotNull
        int count
) {
    public static MedicationsLoadedResponse emptyResponse() {
        return new MedicationsLoadedResponse(emptyList(), 0);
    }
}
