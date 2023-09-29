package com.github.chMatvey.dronedelivery.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DeliveryItemId implements Serializable {
    @Column(name = "delivery_id")
    int deliveryId;

    @Column(name = "medication_id")
    int medicationId;
}
