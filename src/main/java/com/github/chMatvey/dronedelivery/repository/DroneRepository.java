package com.github.chMatvey.dronedelivery.repository;

import com.github.chMatvey.dronedelivery.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Integer> {
}
