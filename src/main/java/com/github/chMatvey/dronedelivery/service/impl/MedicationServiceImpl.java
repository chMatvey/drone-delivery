package com.github.chMatvey.dronedelivery.service.impl;

import com.github.chMatvey.dronedelivery.exception.MedicationImageNotFoundException;
import com.github.chMatvey.dronedelivery.repository.MedicationRepository;
import com.github.chMatvey.dronedelivery.service.MedicationService;
import com.github.chMatvey.dronedelivery.web.dto.MedicationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
    @Override
    public byte[] getMedicationImage(int medicationId) {
        return repository.findById(medicationId)
                .orElseThrow(MedicationImageNotFoundException::new)
                .getImage()
                .getData();
    }
}
