package com.github.chMatvey.web.response;

import com.github.chMatvey.model.Drone;
import com.github.chMatvey.model.DroneModel;
import com.github.chMatvey.model.DroneState;
import lombok.Builder;

@Builder
public record DroneResponse(
        int id,
        String serialNumber,
        DroneModel model,
        int weightLimit,
        int batteryCapacity,
        DroneState state
) {
    public static DroneResponse create(Drone drone) {
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
