package com.github.chMatvey.dronedelivery.web.impl;

import com.github.chMatvey.dronedelivery.web.DroneController;
import com.github.chMatvey.dronedelivery.web.dto.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/drones")
public class DroneControllerImpl implements DroneController {
    @PostMapping("/register")
    @Override
    public DroneRegistrationResponse registerDrone(@RequestBody @Valid DroneRegistrationRequest request) {
        return null;
    }

    @PostMapping("/medications/{droneId}")
    @ResponseStatus(CREATED)
    @Override
    public MedicationsLoadResponse loadMedicationItems(@PathVariable int droneId,
                                                       @RequestBody @Valid MedicationsLoadRequest request) {
        return null;
    }

    @GetMapping("/medications/{droneId}")
    @Override
    public MedicationsLoadedResponse getLoadedMedicationToDone(@PathVariable int droneId) {
        return null;
    }

    @GetMapping("/available")
    @Override
    public DronesAvailableResponse getAvailableDrones() {
        return null;
    }

    @GetMapping("/battery-level/{droneId}")
    @Override
    public DroneBatteryLevelResponse getDroneBatteryLevel(@PathVariable int droneId) {
        return null;
    }
}
