package com.veterinaria.services.Impl;

import com.veterinaria.entities.PetEntity;
import com.veterinaria.entities.RoleEntity;
import com.veterinaria.entities.UserEntity;
import com.veterinaria.repositories.PetRepository;
import com.veterinaria.repositories.RoleRepository;
import com.veterinaria.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PetEntityServiceImpl petEntityServiceImpl;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("El usuario " + username + " no existe"));

        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());

        return new User(userEntity.getUsername(),
                    userEntity.getPassword(),
                    true, true, true, true,
                    authorities);
    }

    public ResponseEntity<?> deleteUser(Long id) {
        List<PetEntity> pets = petEntityServiceImpl.getPetsByUser(id);
        pets.forEach(pet -> {
            petEntityServiceImpl.deletePet(pet.getId());
        });

        UserEntity user = userRepository.findById(id).orElse(null);

        RoleEntity role = user.getRoles().stream().reduce((role1, role2) -> role1).get();
        roleRepository.deleteById(role.getId());

        userRepository.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
}
