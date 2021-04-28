package com.example.med_id.repositories;
import com.example.med_id.model.MedicalFacility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalFacilityRepo extends JpaRepository<MedicalFacility, Long> {
}
