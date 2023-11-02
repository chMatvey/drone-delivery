package com.github.chMatvey.service;

import com.github.chMatvey.model.Drone;
import com.github.chMatvey.web.request.DroneRegisterRequest;
import com.github.chMatvey.web.response.DroneRegisterResponse;
import com.github.chMatvey.web.response.DroneResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

import static com.github.chMatvey.model.DroneState.IDLE;

@ApplicationScoped
public class DroneService {
    public List<DroneResponse> availableDrones() {
        return Drone.available().stream()
                .map(DroneResponse::create)
                .toList();
    }

    @Transactional
    public DroneRegisterResponse registerDrone(DroneRegisterRequest request) {
        Drone drone = Drone.builder()
                .serialNumber(request.serialNumber())
                .model(request.model())
                .weightLimit(request.weightLimit())
                .batteryCapacity(100)
                .state(IDLE)
                .build();

        drone.persist();

        return new DroneRegisterResponse(drone.getId());
    }
}
