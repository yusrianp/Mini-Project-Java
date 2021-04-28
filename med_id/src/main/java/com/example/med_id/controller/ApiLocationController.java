package com.example.med_id.controller;

import com.example.med_id.repositories.LocationRepo;
import com.example.med_id.model.Location;
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
public class ApiLocationController {
    @Autowired
    private LocationRepo locationRepo;

    //  Menampilkan data
    @GetMapping("/location")
    public ResponseEntity<List<Location>> GetAllLocation(){
        try {
            List<Location> location = this.locationRepo.findAll();
            return new ResponseEntity<>(location, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
