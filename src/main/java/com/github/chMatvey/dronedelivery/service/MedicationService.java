package com.github.chMatvey.dronedelivery.service;

import com.github.chMatvey.dronedelivery.web.dto.MedicationResponse;

import java.util.List;

public interface MedicationService {
    List<MedicationResponse> getAllMedications();

    byte[] getMedicationImage(int medicationId);
}
