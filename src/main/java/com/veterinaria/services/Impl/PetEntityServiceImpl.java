package com.veterinaria.services.Impl;

import com.veterinaria.entities.PetEntity;
import com.veterinaria.repositories.PetRepository;
import com.veterinaria.services.PetEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetEntityServiceImpl implements PetEntityService {

    @Autowired
    PetRepository petRepository;

    public PetEntity savePet(PetEntity petEntity) {
        return petRepository.save(petEntity);
    }

    public List<PetEntity> getPets() {
        return petRepository.findAll();
    }

    public PetEntity getPet(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    public PetEntity updatePet(PetEntity petEntity) {
        PetEntity petEntityToUpdate = petRepository.findById(petEntity.getId()).orElse(null);
        if (petEntityToUpdate != null) {
            petEntityToUpdate.setName(petEntity.getName());
            petEntityToUpdate.setBirthdate(petEntity.getBirthdate());
            petEntityToUpdate.setSpecie(petEntity.getSpecie());
            petEntityToUpdate.setRace(petEntity.getRace());
            petEntityToUpdate.setWeight(petEntity.getWeight());
            petEntityToUpdate.setUser(petEntity.getUser());
            petRepository.save(petEntityToUpdate);
        }
        return petEntityToUpdate;
    }

    public List<PetEntity> getPetsByUser(Long id) {
        return petRepository.findByUserId(id);
    }

    public PetEntity getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    public PetEntity deletePet(Long id) {
        PetEntity petEntity = petRepository.findById(id).orElse(null);
        if (petEntity != null) {
            petRepository.delete(petEntity);
        }
        return petEntity;
    }

}
