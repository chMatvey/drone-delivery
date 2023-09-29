package com.github.chMatvey.dronedelivery.service.exception;

public class MedicationNotFoundException extends RuntimeException {
    public MedicationNotFoundException() {
        super("Medication not found");
    }
}
