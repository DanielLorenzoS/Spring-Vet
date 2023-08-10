package com.veterinaria.controllers;

import com.veterinaria.controllers.request.PetDTO;
import com.veterinaria.entities.PetEntity;
import com.veterinaria.entities.UserEntity;
import com.veterinaria.repositories.PetRepository;
import com.veterinaria.repositories.UserRepository;
import com.veterinaria.services.PetEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "*")
public class PetEntityController {

    @Autowired
    PetEntityService petEntityService;

    @Autowired
    PetRepository petRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/")
    public PetEntity savePet(@RequestBody PetEntity petEntity) {
        System.out.println(petEntity);
        return petEntityService.savePet(petEntity);
    }

    @GetMapping("/")
    public List<PetEntity> getPets() {
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

    /*@GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable Long id) {
        byte[] data = imageService.obtenerImagenPorId(id);
        HttpHeaders headers = new HttpHeaders();
        // Configurar los encabezados para el tipo de contenido adecuado
        headers.setContentType(MediaType.IMAGE_JPEG); // Por ejemplo, para imágenes JPEG
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @PostMapping("/imagen")
    public ResponseEntity<String> guardarImagen(@RequestBody Map<String, Object> data) {
        Long id = Long.parseLong(data.get("id").toString());
        byte[] bytes = (byte[]) data.get("imagen");
        String imageId = imageService.guardarImagen(bytes, id);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen");
    }*/


    @DeleteMapping("/{id}")
    public PetEntity deletePet(@PathVariable Long id) {
        return petEntityService.deletePet(id);
    }

    @GetMapping("/users")
    public List<PetDTO> getUserWithPets() {
        List<UserEntity> users = userRepository.findAll();
        List<PetDTO> pets = new ArrayList<>();
        users.forEach(user -> {
            PetDTO tempPet = new PetDTO();
            List<PetEntity> tempListPet = petRepository.findByUserId(user.getId());
            tempListPet.forEach(temp -> {
                tempPet.setId(temp.getId());
                tempPet.setName(temp.getName());
                tempPet.setRace(temp.getRace());
                tempPet.setSpecie(temp.getSpecie());
                tempPet.setUserId(user.getId());
                tempPet.setUserUsername(user.getUsername());
                pets.add(tempPet);
            });
        });
        return pets;
    }
}
