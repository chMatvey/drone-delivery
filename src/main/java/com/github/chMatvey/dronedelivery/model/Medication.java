package com.github.chMatvey.dronedelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
@Table(name = "medications")
public class Medication {
    @Id
    @SequenceGenerator(name = "medications_id_seq", sequenceName = "medications_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "medications_id_seq")
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int weight;

    @Column(nullable = false)
    String code;

    @OneToOne(fetch = LAZY, orphanRemoval = true)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    MedicationImage image;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medication that = (Medication) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
