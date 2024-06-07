package com.fresh.coding.carshow.repositories;

import com.fresh.coding.carshow.entities.Appointment;
import com.fresh.coding.carshow.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByStatus(AppointmentStatus status);
}
