package com.github.chMatvey.dronedelivery.service.exception;

public class UnexpectedDroneStatusException extends RuntimeException {
    public UnexpectedDroneStatusException() {
        super("Unexpected drone status");
    }
}
