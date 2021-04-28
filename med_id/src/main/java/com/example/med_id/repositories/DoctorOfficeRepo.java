package com.example.med_id.repositories;

import com.example.med_id.model.DoctorOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorOfficeRepo extends JpaRepository<DoctorOffice, Long> {
    @Query("FROM DoctorOffice WHERE isDelete = false")
    List<DoctorOffice> GetAvailable();
}
