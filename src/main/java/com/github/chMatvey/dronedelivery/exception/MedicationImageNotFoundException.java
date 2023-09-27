package com.github.chMatvey.dronedelivery.exception;

public class MedicationImageNotFoundException extends RuntimeException {
    public MedicationImageNotFoundException() {
        super("Medication image not found");
    }
}
