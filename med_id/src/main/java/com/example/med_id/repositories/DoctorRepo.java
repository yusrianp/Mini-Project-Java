package com.example.med_id.repositories;

import com.example.med_id.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    @Query("FROM Doctor WHERE BiodataId = ?1")
    Optional<Doctor> GetDoctorByUserId(Long id);

//    @Query("FROM Doctor WHERE BiodataIdId =  ")
}
