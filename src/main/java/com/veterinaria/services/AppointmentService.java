package com.veterinaria.services;

import com.veterinaria.controllers.request.AppointmentWithUserDTO;
import com.veterinaria.entities.AppointmentEntity;

import java.util.List;

public interface AppointmentService {

    AppointmentEntity getAppointmentById(Long id);

    AppointmentEntity saveAppointment(AppointmentEntity appointment);

    AppointmentEntity deleteAppointment(Long id);

    List<AppointmentEntity> getAllAppointments();

    List<AppointmentEntity> findAppointmentByUserId(Long id);

    AppointmentEntity findAppointmentByDoctorsId(Long id);

    List<AppointmentWithUserDTO> getAllAppointmentsWithUsers();
}
