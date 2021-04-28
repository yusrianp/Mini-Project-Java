package com.example.med_id.controller;

import com.example.med_id.model.Doctor;
import com.example.med_id.model.DoctorTreatment;
import com.example.med_id.model.User;
import com.example.med_id.repositories.DoctorRepo;
import com.example.med_id.repositories.DoctorTreatmentRepo;
import com.example.med_id.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiDoctorTreatmentController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private DoctorTreatmentRepo doctorTreatmentRepo;

    @GetMapping("/doctortreatment")
    public ResponseEntity<Object> GetAllDoctorTreatment()
    {
        try
        {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

            Optional<Doctor> currentdokter = this.doctorRepo.GetDoctorByUserId(userDetail.get().getBiodataId());
            List<DoctorTreatment> doctorTreatments = this.doctorTreatmentRepo.notDelete(currentdokter.get().getId());
            return new ResponseEntity<>(doctorTreatments, HttpStatus.OK);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/doctortreatment")
    public ResponseEntity<Object> SaveDoctorTreatment(@RequestBody DoctorTreatment doctorTreatment)
    {
//            DoctorTreatment doctorTreatmentData = this.doctorTreatmentRepo.save(doctorTreatment);
        try
        {

            doctorTreatment.setCreatedOn(new Date());
            doctorTreatment.setDelete(false);
            this.doctorTreatmentRepo.save(doctorTreatment);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("doctortreatment/{id}")
    public ResponseEntity<List<DoctorTreatment>> GetDoctorTreatmentById(@PathVariable("id") Long id)
    {
        try
        {
            Optional<Doctor> doctorBiodata = this.doctorRepo.GetDoctorByUserId(id);
            if (doctorBiodata.isPresent())
            {
                Optional<DoctorTreatment> doctorTreatment = this.doctorTreatmentRepo.GetDoctorTreatmentByDoctorId(doctorBiodata.get().getId());
                System.out.println(doctorTreatment);
                if (doctorTreatment.isPresent()){
                    ResponseEntity responseEntity = new ResponseEntity<>(doctorTreatment, HttpStatus.OK);
                    return responseEntity;
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else
            {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/deletedoctortreatment/{id}")
    public ResponseEntity<List<DoctorTreatment>> DeleteDoctorTreatment(@RequestBody DoctorTreatment doctorTreatment,
                                                                              @PathVariable ("id") Long id){
        try{
            Optional<DoctorTreatment> doctorTreatmentData = this.doctorTreatmentRepo.findById(id);
            if(doctorTreatmentData.isPresent()){
//                long a = 1;
                doctorTreatment.setId(id);
                doctorTreatment.setDelete(true);
                doctorTreatment.setDeletedOn(new Date());
                this.doctorTreatmentRepo.save(doctorTreatment);

                ResponseEntity responseEntity = new ResponseEntity<>( doctorTreatment, HttpStatus.OK);
                return responseEntity;
            } else
                return ResponseEntity.notFound().build();
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/deletedoctortreatment/{id}")
    public ResponseEntity<List<DoctorTreatment>> GetDeleteDoctorTreatmentById(@PathVariable ("id") Long id){
        try{
            Optional<DoctorTreatment> doctorTreatment = this.doctorTreatmentRepo.findById(id);
            if(doctorTreatment.isPresent()){
                ResponseEntity responseEntity = new ResponseEntity<>( doctorTreatment, HttpStatus.OK);
                return responseEntity;
            } else
                return ResponseEntity.notFound().build();
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
