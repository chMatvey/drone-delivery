package com.github.chMatvey.dronedelivery.web.request;

import com.github.chMatvey.dronedelivery.model.Medication;
import com.github.chMatvey.dronedelivery.web.response.MedicationResponse;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;

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
                Medication.builder().id(2).name("Aspirin").weight(100).code("ABC123").createdAt(now()).build(),
                Medication.builder().id(5).name("Ibuprofen").weight(50).code("DEF456").createdAt(now()).build(),
                Medication.builder().id(7).name("Acetaminophen").weight(180).code("GHI789").createdAt(now()).build()
        );
    }
}