package com.github.chMatvey.dronedelivery.web;

import com.github.chMatvey.dronedelivery.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Drone API")
public interface DroneController {
    @Operation(summary = "Register drone")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Drone successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters")
    })
    DroneRegistrationResponse registerDrone(DroneRegistrationRequest request);

    @Operation(summary = "Load medication items to drone")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Medications successfully loaded to drone"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters"),
            @ApiResponse(responseCode = "404", description = "Drone not found by id"),
            @ApiResponse(responseCode = "409", description = "Can not load medications. Drone state is not IDLE.")
    })
    MedicationsLoadResponse loadMedicationItems(int droneId, MedicationsLoadRequest request);

    @Operation(summary = "Get loaded medication items list from drone")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Drone not found by id")
    })
    MedicationsLoadedResponse getLoadedMedicationToDone(int droneId);

    @Operation(summary = "Get available for loading drones")
    DronesAvailableResponse getAvailableDrones();

    @Operation(summary = "Get drone battery level")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Drone not found by id")
    })
    DroneBatteryLevelResponse getDroneBatteryLevel(int droneId);
}
