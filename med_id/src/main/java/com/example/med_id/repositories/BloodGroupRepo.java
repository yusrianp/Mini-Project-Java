package com.example.med_id.repositories;

import com.example.med_id.model.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodGroupRepo extends JpaRepository<BloodGroup, Long> {
}