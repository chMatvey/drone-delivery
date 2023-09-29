package com.github.chMatvey.dronedelivery.service.exception;

public class DroneNotFoundException extends RuntimeException {
    public DroneNotFoundException() {
        super("Drone not found");
    }
}
