package com.example.med_id.controller;
import com.example.med_id.model.MedicalFacility;
import com.example.med_id.repositories.MedicalFacilityRepo;
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
public class ApiMedicalFacilityController {
    @Autowired
    private MedicalFacilityRepo medicalFacilityRepo;

    //  Menampilkan data
    @GetMapping("/medicalfacility")
    public ResponseEntity<List<MedicalFacility>> GetAllMedicalFacility(){
        try {
            List<MedicalFacility> medicalFacilitiy = this.medicalFacilityRepo.findAll();
            return new ResponseEntity<>(medicalFacilitiy, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
