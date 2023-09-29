package com.github.chMatvey.dronedelivery.repository;

import com.github.chMatvey.dronedelivery.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    @Query("select medication from Medication medication join fetch medication.image where medication.id = ?1")
    Optional<Medication> findByIdJoinImage(int medicationId);
}
