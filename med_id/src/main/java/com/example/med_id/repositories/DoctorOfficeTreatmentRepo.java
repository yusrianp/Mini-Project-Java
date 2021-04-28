package com.example.med_id.repositories;

import com.example.med_id.model.DoctorOfficeTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorOfficeTreatmentRepo extends JpaRepository<DoctorOfficeTreatment, Long> {
    @Query("FROM DoctorOfficeTreatment WHERE isDelete = false")
    List<DoctorOfficeTreatment> GetAvailable();
}
