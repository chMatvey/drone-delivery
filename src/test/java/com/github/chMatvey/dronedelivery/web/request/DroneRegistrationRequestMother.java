package com.github.chMatvey.dronedelivery.web.request;

import lombok.experimental.UtilityClass;

import static com.github.chMatvey.dronedelivery.model.DroneModel.CRUISERWEIGHT;

@UtilityClass
public class DroneRegistrationRequestMother {
    public static DroneRegistrationRequest droneRegistrationRequest() {
        return DroneRegistrationRequest.builder()
                .serialNumber("DRN98765")
                .model(CRUISERWEIGHT)
                .weightLimit(420)
                .build();
    }
}