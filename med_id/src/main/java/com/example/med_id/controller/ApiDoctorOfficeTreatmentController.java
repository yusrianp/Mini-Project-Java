package com.example.med_id.controller;

import com.example.med_id.repositories.DoctorOfficeTreatmentRepo;
import com.example.med_id.model.DoctorOfficeTreatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiDoctorOfficeTreatmentController {
    @Autowired
    private DoctorOfficeTreatmentRepo doctorOfficeTreatmentRepo;

    //  Menampilkan data
    @GetMapping("/doctor-office-treatment")
    public ResponseEntity<List<DoctorOfficeTreatment>> GetAllDoctorOfficeTreatment(){
        try {
            List<DoctorOfficeTreatment> doctorOfficeTreatment = this.doctorOfficeTreatmentRepo.GetAvailable();
            return new ResponseEntity<>(doctorOfficeTreatment, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
