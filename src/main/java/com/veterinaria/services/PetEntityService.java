package com.veterinaria.services;

import com.veterinaria.entities.PetEntity;

import java.util.List;

public interface PetEntityService {

    PetEntity savePet(PetEntity petEntity);

    List<PetEntity> getPets();

    PetEntity getPet(Long id);

    List<PetEntity> getPetsByUser(Long id);

    PetEntity updatePet(PetEntity petEntity);

    PetEntity deletePet(Long id);
}
