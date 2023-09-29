package com.github.chMatvey.dronedelivery.service.exception;

public class MedicationsWeightOverTheLimitException extends RuntimeException {
    public MedicationsWeightOverTheLimitException() {
        super("Medications weight more than drone weight limit");
    }
}
