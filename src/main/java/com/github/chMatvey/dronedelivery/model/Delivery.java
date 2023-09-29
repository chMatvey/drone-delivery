package com.github.chMatvey.dronedelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static jakarta.persistence.CascadeType.ALL;
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
@Table(name = "deliveries")
public class Delivery {
    @Id
    @SequenceGenerator(name = "deliveries_id_seq", sequenceName = "deliveries_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "deliveries_id_seq")
    int id;

    @Column(name = "available_weight_capacity", nullable = false)
    int availableWeightCapacity;

    @Column(nullable = false)
    boolean completed;

    @OneToOne(optional = false)
    @JoinColumn(name = "drone_id", referencedColumnName = "id")
    Drone drone;

    @OneToMany(fetch = LAZY, mappedBy = "delivery", cascade = ALL)
    List<DeliveryItem> deliveryItems;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    public List<Medication> getMedications() {
        return deliveryItems.stream()
                .flatMap(DeliveryItem::toMedicationsStream)
                .toList();
    }

    public DeliveryItem createDeliveryItem(Map.Entry<Medication, Long> medicationPerCount) {
        return DeliveryItem.builder()
                .delivery(this)
                .medication(medicationPerCount.getKey())
                .count(medicationPerCount.getValue().intValue())
                .build();
    }
}
