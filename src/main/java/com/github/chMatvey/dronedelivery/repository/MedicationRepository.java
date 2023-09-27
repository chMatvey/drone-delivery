package com.github.chMatvey.dronedelivery.repository;

import com.github.chMatvey.dronedelivery.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
}
