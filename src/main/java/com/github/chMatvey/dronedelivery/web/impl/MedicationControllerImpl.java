package com.github.chMatvey.dronedelivery.web.impl;

import com.github.chMatvey.dronedelivery.service.MedicationService;
import com.github.chMatvey.dronedelivery.web.MedicationController;
import com.github.chMatvey.dronedelivery.web.request.MedicationCreationRequest;
import com.github.chMatvey.dronedelivery.web.request.MedicationFullUpdateRequest;
import com.github.chMatvey.dronedelivery.web.request.MedicationPartialUpdateRequest;
import com.github.chMatvey.dronedelivery.web.response.MedicationCreationResponse;
import com.github.chMatvey.dronedelivery.web.response.MedicationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG;

@RestController
@RequestMapping("/api/v1/medications")
@RequiredArgsConstructor
public class MedicationControllerImpl implements MedicationController {
    private final MedicationService medicationService;

    @GetMapping
    @Override
    public List<MedicationResponse> getAllMedications() {
        return medicationService.getAllMedications();
    }

    @GetMapping("/{medicationId}")
    @Override
    public MedicationResponse getMedication(@PathVariable int medicationId) {
        return medicationService.getMedication(medicationId);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Override
    public MedicationCreationResponse createMedication(@RequestBody @Valid MedicationCreationRequest request) {
        return medicationService.createMedication(request);
    }

    @PutMapping("/{medicationId}")
    @Override
    public MedicationResponse fullyUpdateMedication(@PathVariable int medicationId,
                                                    @RequestBody @Valid MedicationFullUpdateRequest request) {
        return medicationService.fullyUpdateMedication(medicationId, request);
    }

    @PatchMapping("/{medicationId}")
    @Override
    public MedicationResponse partialUpdateMedication(@PathVariable int medicationId,
                                                      @RequestBody @Valid MedicationPartialUpdateRequest request) {
        return medicationService.partialUpdateMedication(medicationId, request);
    }

    @DeleteMapping("/{medicationId}")
    @Override
    public void deleteMedication(@PathVariable int medicationId) {
        medicationService.deleteMedication(medicationId);
    }

    @GetMapping("/{medicationId}/image")
    @Override
    public ResponseEntity<?> downloadMedicationImage(@PathVariable int medicationId) {
        return ResponseEntity.status(OK)
                .contentType(IMAGE_PNG)
                .body(medicationService.getMedicationImage(medicationId));
    }

    @PostMapping("/{medicationId}/image")
    @Override
    public void uploadMedicationImage(@PathVariable int medicationId,
                                      @RequestParam("image") MultipartFile file) throws IOException {
        medicationService.setMedicationImage(medicationId, file.getBytes());
    }
}
