package com.github.chMatvey.dronedelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery_items")
public class DeliveryItem {
    @Builder.Default
    @EmbeddedId
    DeliveryItemId id = new DeliveryItemId();

    @ManyToOne
    @MapsId("deliveryId")
    Delivery delivery;

    @ManyToOne
    @MapsId("medicationId")
    Medication medication;

    int count;

    public Stream<Medication> toMedicationsStream() {
        return Stream.generate(() -> medication)
                .limit(count);
    }
}
