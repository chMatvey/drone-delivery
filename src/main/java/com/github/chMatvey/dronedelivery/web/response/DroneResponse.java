package com.github.chMatvey.dronedelivery.web.response;

import com.github.chMatvey.dronedelivery.model.Drone;
import com.github.chMatvey.dronedelivery.model.DroneModel;
import com.github.chMatvey.dronedelivery.model.DroneState;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record DroneResponse(
        @NotNull
        int id,

        @NotBlank
        String serialNumber,

        @NotNull
        DroneModel model,

        @NotNull
        int weightLimit,

        @NotNull
        int batteryCapacity,

        @NotNull
        DroneState state
) {
    public static DroneResponse fromModel(Drone drone) {
        return DroneResponse.builder()
                .id(drone.getId())
                .serialNumber(drone.getSerialNumber())
                .model(drone.getModel())
                .weightLimit(drone.getWeightLimit())
                .batteryCapacity(drone.getBatteryCapacity())
                .state(drone.getState())
                .build();
    }
}
