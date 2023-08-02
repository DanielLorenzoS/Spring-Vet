package com.veterinaria.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pets")
public class PetEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String sex;

    @NotNull @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    @NotBlank
    private String specie;

    @NotBlank
    private String race;

    @NotNull
    private float weight;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
