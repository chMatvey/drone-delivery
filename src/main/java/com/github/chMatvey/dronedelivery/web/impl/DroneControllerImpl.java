package com.github.chMatvey.dronedelivery.web.impl;

import com.github.chMatvey.dronedelivery.service.DroneService;
import com.github.chMatvey.dronedelivery.web.DroneController;
import com.github.chMatvey.dronedelivery.web.request.DroneRegistrationRequest;
import com.github.chMatvey.dronedelivery.web.request.MedicationsLoadRequest;
import com.github.chMatvey.dronedelivery.web.response.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/drones")
@RequiredArgsConstructor
public class DroneControllerImpl implements DroneController {
    private final DroneService droneService;

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    @Override
    public DroneRegistrationResponse registerDrone(@RequestBody @Valid DroneRegistrationRequest request) {
        return droneService.registerDrone(request);
    }

    @PostMapping("/{droneId}/medications")
    @Override
    public MedicationsLoadResponse loadMedication(@PathVariable int droneId,
                                                  @RequestBody @Valid MedicationsLoadRequest request) {
        return droneService.loadMedicationItems(droneId, request);
    }

    @GetMapping("/{droneId}/medications")
    @Override
    public MedicationsLoadedResponse getLoadedMedication(@PathVariable int droneId) {
        return droneService.getLoadedMedication(droneId);
    }

    @GetMapping("/available")
    @Override
    public AvailableDronesResponse getAvailableDrones() {
        return droneService.getAvailableDrones();
    }

    @GetMapping("/battery-level/{droneId}")
    @Override
    public DroneBatteryLevelResponse getDroneBatteryLevel(@PathVariable int droneId) {
        return droneService.getDroneBatteryLevel(droneId);
    }
}
