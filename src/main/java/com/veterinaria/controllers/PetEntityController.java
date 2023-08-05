package com.veterinaria.controllers;

import com.veterinaria.entities.PetEntity;
import com.veterinaria.services.PetEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
