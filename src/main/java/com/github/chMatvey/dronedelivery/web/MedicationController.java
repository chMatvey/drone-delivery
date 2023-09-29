package com.github.chMatvey.dronedelivery.web;

import com.github.chMatvey.dronedelivery.web.request.MedicationCreationRequest;
import com.github.chMatvey.dronedelivery.web.request.MedicationFullUpdateRequest;
import com.github.chMatvey.dronedelivery.web.request.MedicationPartialUpdateRequest;
import com.github.chMatvey.dronedelivery.web.response.MedicationCreationResponse;
import com.github.chMatvey.dronedelivery.web.response.MedicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Medication API")
public interface MedicationController {
    @Operation(summary = "Get all medications")
    List<MedicationResponse> getAllMedications();

    @Operation(summary = "Get medication")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Medication not found")
    })
    MedicationResponse getMedication(int medicationId);

    @Operation(summary = "Create medication")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Medication successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters")
    })
    MedicationCreationResponse createMedication(@RequestBody MedicationCreationRequest request);

    @Operation(summary = "Fully update medication")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Medication successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters"),
            @ApiResponse(responseCode = "404", description = "Medication not found")
    })
    MedicationResponse fullyUpdateMedication(int medicationId, @RequestBody MedicationFullUpdateRequest request);

    @Operation(summary = "Partial update medication")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Medication successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters"),
            @ApiResponse(responseCode = "404", description = "Medication not found")
    })
    MedicationResponse partialUpdateMedication(int medicationId, @RequestBody MedicationPartialUpdateRequest request);

    @Operation(summary = "Delete medication")
    void deleteMedication(int medicationId);

    @Operation(summary = "Download medication image")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Medication image not found by medication Id")
    })
    ResponseEntity<?> downloadMedicationImage(int medicationId);

    @Operation(summary = "Download medication image")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Medication image not found by medication Id"),
            @ApiResponse(responseCode = "413", description = "Content Too Large")
    })
    void uploadMedicationImage(int medicationId, MultipartFile file) throws IOException;
}
