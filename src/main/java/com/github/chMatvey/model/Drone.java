package com.github.chMatvey.model;

import com.github.chMatvey.model.converter.DroneModelConverter;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static com.github.chMatvey.model.DroneState.IDLE;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "drones")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drone extends PanacheEntityBase {
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

    public static List<Drone> available() {
        return list("state", IDLE);
    }
}
