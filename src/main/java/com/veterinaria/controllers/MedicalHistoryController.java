package com.veterinaria.controllers;

import com.veterinaria.entities.MedicalHistoryEntity;
import com.veterinaria.services.MedicalHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical")
public class MedicalHistoryController {
/*
    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @PostMapping("/")
    public MedicalHistoryEntity saveMedicalHistory(@RequestBody MedicalHistoryEntity medicalHistoryEntity) {
         try {
            return medicalHistoryService.save(medicalHistoryEntity);
        } catch (Exception e) {
             e.printStackTrace();
             return null;
        }
    }

    @GetMapping("/")
    public List<MedicalHistoryEntity> getMedicalHistory() {
        return medicalHistoryService.getAll();
    }*/
}
