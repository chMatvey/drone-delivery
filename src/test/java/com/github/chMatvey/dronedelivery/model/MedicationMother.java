package com.github.chMatvey.dronedelivery.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MedicationMother {
    public static Medication aspirin() {
        return Medication.builder()
                .id(2)
                .name("Aspirin")
                .weight(100)
                .code("ABC123")
                .build();
    }

    public static Medication ibuprofen() {
        return Medication.builder()
                .id(5)
                .name("Ibuprofen")
                .weight(50)
                .code("DEF456")
                .build();
    }

    public static Medication acetaminophen() {
        return Medication.builder()
                .id(7)
                .name("Acetaminophen")
                .weight(180)
                .code("GHI789")
                .build();
    }
}
