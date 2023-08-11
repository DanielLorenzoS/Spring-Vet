package com.veterinaria.controllers;

import com.veterinaria.controllers.request.UpdateUserDTO;
import com.veterinaria.controllers.request.UpdateUserPassDTO;
import com.veterinaria.entities.ERole;
import com.veterinaria.entities.RoleEntity;
import com.veterinaria.entities.UserEntity;
import com.veterinaria.repositories.UserRepository;
import com.veterinaria.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class UserEntityController {

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public List<UserEntity> getAllUsers() {
        return userEntityService.getAllUsers();
    }

    @PutMapping("/update")
    public UserEntity updateUser(@RequestBody UpdateUserPassDTO updateUserPassDTO) {
        UserEntity userEntity = (UserEntity) userEntityService.getUserByEmail(updateUserPassDTO.getEmail()).getBody();
        assert userEntity != null;
        userEntity.setPassword(updateUserPassDTO.getPassword());
        return userEntityService.updateUser(Optional.of(userEntity));
    }

    @PutMapping("/update/user")
    public UserEntity updateAllUser(@RequestBody UpdateUserDTO updateUserDTO) {
        System.out.println(updateUserDTO);
        Optional<UserEntity> userEntity = userRepository.findById((long) updateUserDTO.getId());
        assert userEntity.isPresent();
        userEntity.get().setUsername(updateUserDTO.getUsername());
        userEntity.get().setEmail(updateUserDTO.getEmail());
        userEntity.get().setPhone(updateUserDTO.getPhone());
        userEntity.get().setAddress(updateUserDTO.getAddress());
        return userEntityService.updateUser(userEntity);
    }

    @PutMapping("/updatetoadmin")
    public UserEntity updateUser(@RequestBody UserEntity userEntity) {
        Set<RoleEntity> roles = userEntity.getRoles();
        RoleEntity role = userEntity.getRoles().stream().reduce((role1, role2) -> role1).get();
        role.setName(ERole.valueOf("ADMIN"));
        return userEntityService.updateUser(Optional.of(userEntity));
    }

    @GetMapping("/user/{username}")
    public UserEntity getUserByUsername(@PathVariable String username) {
        return userEntityService.getUserByUsername(username);
    }

    @GetMapping("/roles/{role}")
    public List<UserEntity> getAllUsersByRole(@PathVariable String role) {
        return userEntityService.getAllUsersByRoles(role);
    }

    @GetMapping("/user/email")
    public ResponseEntity<?> getUserByEmail(String email) {
        return userEntityService.getUserByEmail(email);
    }

    @GetMapping("/user/pet/{id}")
    public UserEntity getUserByPetId(@PathVariable int id) {
        return userEntityService.getUserByPetId((long) id);
    }
}
