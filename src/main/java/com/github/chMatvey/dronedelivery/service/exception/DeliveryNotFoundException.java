package com.github.chMatvey.dronedelivery.service.exception;

public class DeliveryNotFoundException extends RuntimeException {
    public DeliveryNotFoundException() {
        super("Delivery not found");
    }
}
