package com.github.chMatvey.dronedelivery.web.request;

import com.github.chMatvey.dronedelivery.model.Medication;
import com.github.chMatvey.dronedelivery.web.response.MedicationResponse;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;

import static com.github.chMatvey.dronedelivery.model.MedicationMother.*;
import static java.time.LocalDateTime.now;

@UtilityClass
public class MedicationsLoadRequestMother {
    public static MedicationsLoadRequest medicationsLoadRequest() {
        return MedicationsLoadRequest.builder()
                .medicationsIds(List.of(2, 2, 5, 7))
                .build();
    }

    public static MedicationsLoadRequest medicationsLoadRequestWithOverweight() {
        return MedicationsLoadRequest.builder()
                .medicationsIds(List.of(2, 2, 5, 7, 7))
                .build();
    }

    public static List<Medication> medications() {
        return List.of(
                aspirin(),
                ibuprofen(),
                acetaminophen()
        );
    }
}