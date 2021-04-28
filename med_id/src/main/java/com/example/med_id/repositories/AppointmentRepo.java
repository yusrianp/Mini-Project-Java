package com.example.med_id.repositories;

import com.example.med_id.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    @Query("FROM Appointment WHERE isDelete = false")
    List<Appointment> GetAvailable();
}
