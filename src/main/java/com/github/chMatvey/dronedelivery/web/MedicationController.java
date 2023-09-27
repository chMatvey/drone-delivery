package com.github.chMatvey.dronedelivery.web;

import com.github.chMatvey.dronedelivery.web.dto.MedicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Medication API")
public interface MedicationController {
    @Operation(summary = "Get all medications")
    List<MedicationResponse> getAll();

    @Operation(summary = "Download medication image")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Medication image not found by medication Id")
    })
    ResponseEntity<?> getMedicationImage(int medicationId);
}
