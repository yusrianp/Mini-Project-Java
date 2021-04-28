package com.example.med_id.controller;
import com.example.med_id.repositories.DoctorOfficeScheduleRepo;
import com.example.med_id.model.DoctorOfficeSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiDoctorOfficeScheduleController {
    @Autowired
    private DoctorOfficeScheduleRepo doctorOfficeScheduleRepo;

    //  Menampilkan data
    @GetMapping("/doctor-office-schedule")
    public ResponseEntity<List<DoctorOfficeSchedule>> GetAllDoctorOfficeSchedule(){
        try {
            List<DoctorOfficeSchedule> doctorOfficeSchedule = this.doctorOfficeScheduleRepo.GetAvailable();
            return new ResponseEntity<>(doctorOfficeSchedule, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //  mengedit data(Edit)
    @GetMapping("/doctor-office-schedule/{id}")
    public ResponseEntity<List<DoctorOfficeSchedule>> GetDoctorOfficeScheduleById(@PathVariable("id") Long id) {
        try{
            Optional<DoctorOfficeSchedule> doctorOfficeSchedule = this.doctorOfficeScheduleRepo.findById(id);
            if (doctorOfficeSchedule.isPresent()) {
                ResponseEntity rest = new ResponseEntity<>(doctorOfficeSchedule, HttpStatus.OK);
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

    //  update data(Update)
    @PutMapping("/doctor-office-schedule/{id}")
    public ResponseEntity<Object> UpdateDoctorOfficeSchedule(@RequestBody DoctorOfficeSchedule doctorOfficeSchedule, @PathVariable("id") Long id)
    {
        Optional<DoctorOfficeSchedule> doctorOfficeScheduleData = this.doctorOfficeScheduleRepo.findById(id);

        if (doctorOfficeScheduleData.isPresent())
        {
            long a = 1 ;
            doctorOfficeSchedule.setId(id);
            doctorOfficeSchedule.setModifiedBy(a);
            doctorOfficeSchedule.setModifiedOn(new Date());
            this.doctorOfficeScheduleRepo.save(doctorOfficeSchedule);
            ResponseEntity rest = new ResponseEntity<>("success", HttpStatus.OK);
            return rest;
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
