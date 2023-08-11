package com.veterinaria.services;

import com.veterinaria.entities.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserEntityService {

    List<UserEntity> getAllUsers();

    UserEntity updateUser(Optional<UserEntity> userEntity);

    List<UserEntity> getAllUsersByRoles(String role);

    ResponseEntity<?> getUserByEmail(String email);

    UserEntity getUserByUsername(String username);

    UserEntity getUserByPetId(Long id);
}
