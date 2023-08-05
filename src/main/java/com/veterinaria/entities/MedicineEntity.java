package com.veterinaria.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "medicines")
public class MedicineEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String fabricator;

    @NotBlank
    private String type;

    @NotBlank
    private String dose;

    @NotBlank
    private String via;

    @NotBlank
    @Column(name = "medication_interval")
    private String interval;

    @NotBlank
    private String recommended;

    @NotBlank
    private String storage;

    @NotBlank
    private String indications;

    @NotBlank
    private String contraindications;

    @NotBlank
    private String adverseReactions;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "prescription_id")
    private PrescriptionEntity prescription;
}
