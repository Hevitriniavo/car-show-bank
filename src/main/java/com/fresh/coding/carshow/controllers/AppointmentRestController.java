package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.requests.AppointmentRequest;
import com.fresh.coding.carshow.dtos.requests.UpdateStatus;
import com.fresh.coding.carshow.dtos.responses.AppointmentSummarized;
import com.fresh.coding.carshow.enums.AppointmentStatus;
import com.fresh.coding.carshow.services.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentRestController {
    private final AppointmentService appointmentService;

    @PostMapping
    public AppointmentSummarized createAppointment(@RequestBody @Valid AppointmentRequest appointmentRequest) {
        return appointmentService.createAppointment(appointmentRequest);
    }

    @GetMapping
    public List<AppointmentSummarized> getAllAppointments() {
        return appointmentService.findAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentSummarized getAppointment(@PathVariable Long id) {
        return appointmentService.findAppointment(id);
    }


    @PutMapping("/{id}")
    public AppointmentSummarized updateStatusAppointment(@PathVariable Long id, @RequestBody UpdateStatus status) {
        return appointmentService.updateStatusAppointment(id, status.appointmentStatus().name());
    }

    @GetMapping("/status/{status}")
    public List<AppointmentSummarized> getAllAppointmentByStatus(@PathVariable AppointmentStatus status) {
        return appointmentService.findAllAppointmentByStatus(status);
    }


    @GetMapping("/status-not-equals/{status}")
    public List<AppointmentSummarized> getAllByStatusNotEquals(@PathVariable AppointmentStatus status) {
        return appointmentService.findAllByStatusNotEquals(status);
    }
}
