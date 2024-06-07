package com.fresh.coding.carshow.repositories;

import com.fresh.coding.carshow.entities.Appointment;
import com.fresh.coding.carshow.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByStatus(AppointmentStatus status);

    @Query("SELECT a FROM Appointment a WHERE a.status <> ?1")
    List<Appointment> findAllByStatusNotEquals(AppointmentStatus status);
}
