package com.github.chMatvey.dronedelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;

import static jakarta.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medications_images")
public class MedicationImage {
    @Id
    @SequenceGenerator(name = "medications_id_seq", sequenceName = "medications_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "medications_id_seq")
    int id;

    @Lazy
    byte[] data;
}
