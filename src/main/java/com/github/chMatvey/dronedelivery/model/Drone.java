package com.github.chMatvey.dronedelivery.model;

import com.github.chMatvey.dronedelivery.model.converter.DroneModelConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drones")
public class Drone {
    @Id
    @SequenceGenerator(name = "drones_id_seq", sequenceName = "drones_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "drones_id_seq")
    int id;

    @Column(name = "serial_number", nullable = false)
    String serialNumber;

    @Convert(converter = DroneModelConverter.class)
    @Column(nullable = false)
    DroneModel model;

    @Column(name = "weight_limit", nullable = false)
    int weightLimit;

    @Column(name = "battery_capacity", nullable = false)
    int batteryCapacity;

    @Enumerated(STRING)
    @Column(nullable = false)
    DroneState state;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;
}
