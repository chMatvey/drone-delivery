package com.github.chMatvey.dronedelivery.service.exception;

public class MedicationImageNotFoundException extends RuntimeException {
    public MedicationImageNotFoundException() {
        super("Medication image not found");
    }
}
