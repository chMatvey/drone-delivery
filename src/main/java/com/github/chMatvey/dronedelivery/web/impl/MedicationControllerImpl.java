package com.github.chMatvey.dronedelivery.web.impl;

import com.github.chMatvey.dronedelivery.service.MedicationService;
import com.github.chMatvey.dronedelivery.web.MedicationController;
import com.github.chMatvey.dronedelivery.web.dto.MedicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG;

@RestController
@RequestMapping("/api/v1/medications")
@RequiredArgsConstructor
public class MedicationControllerImpl implements MedicationController {
    private final MedicationService medicationService;

    @GetMapping
    @Override
    public List<MedicationResponse> getAll() {
        return medicationService.getAllMedications();
    }

    @GetMapping("/{medicationId}/image")
    @Override
    public ResponseEntity<?> getMedicationImage(@PathVariable int medicationId) {
        return ResponseEntity.status(OK)
                .contentType(IMAGE_PNG)
                .body(medicationService.getMedicationImage(medicationId));
    }
}
