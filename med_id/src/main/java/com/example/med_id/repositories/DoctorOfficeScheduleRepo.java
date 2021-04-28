package com.example.med_id.repositories;
import com.example.med_id.model.DoctorOfficeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorOfficeScheduleRepo extends JpaRepository<DoctorOfficeSchedule, Long> {
    @Query("FROM DoctorOfficeSchedule WHERE isDelete = false")
    List<DoctorOfficeSchedule> GetAvailable();
}
