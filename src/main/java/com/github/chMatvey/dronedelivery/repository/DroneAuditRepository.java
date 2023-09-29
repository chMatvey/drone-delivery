package com.github.chMatvey.dronedelivery.repository;

import com.github.chMatvey.dronedelivery.model.DroneAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneAuditRepository extends JpaRepository<DroneAudit, Integer> {
}
