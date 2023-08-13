package com.veterinaria.repositories;

import com.veterinaria.entities.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Long> {

}
