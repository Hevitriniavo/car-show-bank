package com.fresh.coding.carshow.services;

import com.fresh.coding.carshow.dtos.requests.AppointmentRequest;
import com.fresh.coding.carshow.dtos.responses.AppointmentSummarized;
import com.fresh.coding.carshow.entities.Appointment;
import com.fresh.coding.carshow.enums.AppointmentStatus;

import java.util.List;

public interface AppointmentService {
    AppointmentSummarized createAppointment(AppointmentRequest appointmentRequest);

    List<AppointmentSummarized> findAllAppointments();

    AppointmentSummarized findAppointment(Long id);

    AppointmentSummarized updateStatusAppointment(Long id, String status);

    List<AppointmentSummarized> findAllAppointmentByStatus(AppointmentStatus status);

    List<AppointmentSummarized> findAllByStatusNotEquals(AppointmentStatus status);
}
