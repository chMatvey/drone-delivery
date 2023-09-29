package com.github.chMatvey.dronedelivery.service;

import com.github.chMatvey.dronedelivery.web.request.MedicationCreationRequest;
import com.github.chMatvey.dronedelivery.web.request.MedicationFullUpdateRequest;
import com.github.chMatvey.dronedelivery.web.request.MedicationPartialUpdateRequest;
import com.github.chMatvey.dronedelivery.web.response.MedicationCreationResponse;
import com.github.chMatvey.dronedelivery.web.response.MedicationResponse;

import java.util.List;

public interface MedicationService {
    List<MedicationResponse> getAllMedications();

    MedicationResponse getMedication(int medicationId);

    MedicationCreationResponse createMedication(MedicationCreationRequest request);

    MedicationResponse fullyUpdateMedication(int medicationId, MedicationFullUpdateRequest request);

    MedicationResponse partialUpdateMedication(int medicationId, MedicationPartialUpdateRequest request);

    void deleteMedication(int medicationId);

    byte[] getMedicationImage(int medicationId);

    void setMedicationImage(int medicationId, byte[] data);
}
