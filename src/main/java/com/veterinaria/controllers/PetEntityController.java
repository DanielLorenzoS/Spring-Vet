package com.veterinaria.controllers;

import com.veterinaria.entities.PetEntity;
import com.veterinaria.services.PetEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "*")
public class PetEntityController {

    @Autowired
    PetEntityService petEntityService;

    @PostMapping("/")
    public PetEntity savePet(@RequestBody PetEntity petEntity) {
        System.out.println(petEntity);
        return petEntityService.savePet(petEntity);
    }

    @GetMapping("/")
    public Iterable<PetEntity> getPets() {
        return petEntityService.getPets();
    }

    @PutMapping("/")
    public PetEntity updatePet(@RequestBody PetEntity petEntity) {
        return petEntityService.updatePet(petEntity);
    }

    @GetMapping("/{id}")
    public PetEntity getPet(@PathVariable Long id) {
        return petEntityService.getPet(id);
    }

    @GetMapping("/user/{id}")
    public Iterable<PetEntity> getPetsByUser(@PathVariable int id) {
        return petEntityService.getPetsByUser((long) id);
    }

    @DeleteMapping("/{id}")
    public PetEntity deletePet(@PathVariable Long id) {
        return petEntityService.deletePet(id);
    }

}
