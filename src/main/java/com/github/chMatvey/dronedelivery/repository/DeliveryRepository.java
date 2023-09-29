package com.github.chMatvey.dronedelivery.repository;

import com.github.chMatvey.dronedelivery.model.Delivery;
import com.github.chMatvey.dronedelivery.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    Optional<Delivery> findOneByDroneAndCompletedFalse(Drone drone);

    @Query("select delivery from Delivery delivery join fetch delivery.medications where delivery.completed = false and delivery.drone = ?1")
    Optional<Delivery> findOneByDroneAndCompletedFalseJoinMedications(Drone drone);
}
