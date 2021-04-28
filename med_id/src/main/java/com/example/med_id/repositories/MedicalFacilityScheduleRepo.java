package com.example.med_id.repositories;
import com.example.med_id.model.MedicalFacilitySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicalFacilityScheduleRepo extends JpaRepository<MedicalFacilitySchedule, Long> {
    @Query("FROM MedicalFacilitySchedule WHERE isDelete = false")
    List<MedicalFacilitySchedule> GetAvailable();
}
