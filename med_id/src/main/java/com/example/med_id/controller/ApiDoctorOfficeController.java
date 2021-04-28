package com.example.med_id.controller;
import com.example.med_id.model.DoctorOffice;
import com.example.med_id.repositories.DoctorOfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiDoctorOfficeController {
    @Autowired
    private DoctorOfficeRepo doctorOfficeRepo;

    //  Menampilkan data
    @GetMapping("/doctoroffice")
    public ResponseEntity<List<DoctorOffice>> GetAllDoctorOffice(){
        try {
            List<DoctorOffice> doctorOffice = this.doctorOfficeRepo.GetAvailable();
            return new ResponseEntity<>(doctorOffice, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //  mengedit data(Edit)
    @GetMapping("/doctoroffice/{id}")
    public ResponseEntity<List<DoctorOffice>> GetDoctorOfficeById(@PathVariable("id") Long id) {
        try{
            Optional<DoctorOffice> doctorOffice = this.doctorOfficeRepo.findById(id);
            if (doctorOffice.isPresent()) {
                ResponseEntity rest = new ResponseEntity<>(doctorOffice, HttpStatus.OK);
                return rest;
            } else{
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
