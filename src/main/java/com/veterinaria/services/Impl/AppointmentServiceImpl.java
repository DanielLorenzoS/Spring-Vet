package com.veterinaria.services.Impl;

import com.veterinaria.controllers.request.AppointmentWithUserDTO;
import com.veterinaria.entities.AppointmentEntity;
import com.veterinaria.entities.PetEntity;
import com.veterinaria.entities.UserEntity;
import com.veterinaria.repositories.AppointmentRepository;
import com.veterinaria.services.AppointmentService;
import com.veterinaria.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    UserEntityService userEntityService;

    public List<AppointmentEntity> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public AppointmentEntity getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public AppointmentEntity saveAppointment(AppointmentEntity appointment) {
        return appointmentRepository.save(appointment);
    }

    public AppointmentEntity deleteAppointment(Long id) {
        AppointmentEntity appointment = appointmentRepository.findById(id).orElse(null);
        appointmentRepository.deleteById(id);
        return appointment;
    }

    public List<AppointmentEntity> findAppointmentByUserId(Long id) {
        return appointmentRepository.findAppointmentByUserId(id);
    }

    public AppointmentEntity findAppointmentByDoctorsId(Long id) {
        return appointmentRepository.findAppointmentByDoctorsId(id);
    }

    public List<AppointmentWithUserDTO> getAllAppointmentsWithUsers() {
        List<AppointmentWithUserDTO> appointmentsDTO = new ArrayList<>();
        List<UserEntity> users = userEntityService.getAllUsers();

        for (UserEntity user : users) {
            List<AppointmentEntity> userAppointments = appointmentRepository.findAppointmentByUserId(user.getId());

            for (AppointmentEntity appointment : userAppointments) {
                AppointmentWithUserDTO appointmentWithUserDTO = new AppointmentWithUserDTO();
                appointmentWithUserDTO.setDate(appointment.getDate());
                appointmentWithUserDTO.setType(appointment.getType());
                appointmentWithUserDTO.setReason(appointment.getReason());
                appointmentWithUserDTO.setStatus(appointment.getStatus());
                appointmentWithUserDTO.setUser(user.getUsername());
                appointmentWithUserDTO.setPet(appointment.getPets().stream().reduce((pet1, pet2) -> pet1).get().getName());

                appointmentsDTO.add(appointmentWithUserDTO);
            }
        }

        return appointmentsDTO;
    }

}
