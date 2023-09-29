package com.github.chMatvey.dronedelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drone_audit")
public class DroneAudit {
    @Id
    @SequenceGenerator(name = "drones_audit_id_seq", sequenceName = "drones_audit_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "drones_audit_id_seq")
    int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "drone_id", nullable = false)
    Drone drone;

    @Column(name = "battery_level", nullable = false)
    int batteryLevel;
}
