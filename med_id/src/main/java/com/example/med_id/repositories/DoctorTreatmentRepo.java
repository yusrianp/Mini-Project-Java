package com.example.med_id.repositories;

import com.example.med_id.model.DoctorTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorTreatmentRepo extends JpaRepository<DoctorTreatment, Long> {

    @Query("FROM DoctorTreatment WHERE createdBy = ?1")
    Optional<DoctorTreatment> GetDoctorTreatmentByDoctorId(Long id);

//    @Query("FROM DoctorTreatment WHERE isDelete = false ")
//    List<DoctorTreatment> notDelete();


    @Query("FROM DoctorTreatment WHERE DoctorId = ?1 and isDelete = false ")
    List<DoctorTreatment> notDelete(Long id);
}
