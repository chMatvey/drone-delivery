package com.github.chMatvey.dronedelivery.service.impl;

import com.github.chMatvey.dronedelivery.model.Medication;
import com.github.chMatvey.dronedelivery.model.MedicationImage;
import com.github.chMatvey.dronedelivery.service.exception.MedicationImageNotFoundException;
import com.github.chMatvey.dronedelivery.repository.MedicationRepository;
import com.github.chMatvey.dronedelivery.service.MedicationService;
import com.github.chMatvey.dronedelivery.service.exception.MedicationNotFoundException;
import com.github.chMatvey.dronedelivery.web.request.MedicationCreationRequest;
import com.github.chMatvey.dronedelivery.web.request.MedicationFullUpdateRequest;
import com.github.chMatvey.dronedelivery.web.request.MedicationPartialUpdateRequest;
import com.github.chMatvey.dronedelivery.web.response.MedicationCreationResponse;
import com.github.chMatvey.dronedelivery.web.response.MedicationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.chMatvey.dronedelivery.web.response.MedicationResponse.fromModel;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository repository;

    @Override
    public List<MedicationResponse> getAllMedications() {
        return repository.findAll().stream()
                .map(MedicationResponse::fromModel)
                .toList();
    }

    @Override
    public MedicationResponse getMedication(int medicationId) {
        return repository.findById(medicationId)
                .map(MedicationResponse::fromModel)
                .orElseThrow(MedicationNotFoundException::new);
    }

    @Transactional
    @Override
    public MedicationCreationResponse createMedication(MedicationCreationRequest request) {
        Medication medication = Medication.builder()
                .name(request.name())
                .weight(request.weight())
                .code(request.code())
                .build();

        int id = repository.save(medication).getId();
        return new MedicationCreationResponse(id);
    }

    @Override
    public MedicationResponse fullyUpdateMedication(int medicationId, MedicationFullUpdateRequest request) {
        Medication medication = repository.findById(medicationId)
                .orElseThrow(MedicationNotFoundException::new)
                .setName(request.name())
                .setWeight(request.weight())
                .setCode(request.code());

        return fromModel(repository.save(medication));
    }

    @Override
    public MedicationResponse partialUpdateMedication(int medicationId, MedicationPartialUpdateRequest request) {
        Medication medication = repository.findById(medicationId).orElseThrow(MedicationNotFoundException::new);
        if (request.name() != null)
            medication.setName(request.name());
        if (request.weight() != null)
            medication.setWeight(request.weight());
        if (request.code() != null)
            medication.setCode(request.code());

        return fromModel(repository.save(medication));
    }

    @Override
    public void deleteMedication(int medicationId) {
        repository.deleteById(medicationId);
    }

    @Override
    public byte[] getMedicationImage(int medicationId) {
        return repository.findByIdJoinImage(medicationId)
                .map(Medication::getImage)
                .orElseThrow(MedicationImageNotFoundException::new)
                .getData();
    }

    @Override
    public void setMedicationImage(int medicationId, byte[] data) {
        Medication medication = repository.findById(medicationId).orElseThrow(MedicationNotFoundException::new);
        MedicationImage image = MedicationImage.builder().data(data).build();

        repository.save(medication.setImage(image));
    }
}
