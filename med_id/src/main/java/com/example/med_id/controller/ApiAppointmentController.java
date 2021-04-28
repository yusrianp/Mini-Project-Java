package com.example.med_id.controller;
import com.example.med_id.model.Appointment;
import com.example.med_id.model.CustomerRelation;
import com.example.med_id.model.User;
import com.example.med_id.repositories.AppointmentRepo;
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
public class ApiAppointmentController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    //  Menampilkan data
    @GetMapping("/appointment")
    public ResponseEntity<List<Appointment>> GetAllAppointment(){
        try {
            List<Appointment> appointment = this.appointmentRepo.GetAvailable();
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //  Open form & Save data
    @PostMapping("/appointment")
    public ResponseEntity<Object> SaveAppointment(@RequestBody Appointment appointment){
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

            long a = userDetail.get().getId()  ;
            appointment.setCreatedBy(a);
            appointment.setCreatedOn(new Date());
            this.appointmentRepo.save(appointment);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>("faild",HttpStatus.BAD_REQUEST);
        }
    }

    //  mengedit data(Edit)
    @GetMapping("/appointment/{id}")
    public ResponseEntity<List<CustomerRelation>> GetappointmentById(@PathVariable("id") Long id) {
        try{
            Optional<Appointment> appointment = this.appointmentRepo.findById(id);
            if (appointment.isPresent()) {
                ResponseEntity rest = new ResponseEntity<>(appointment, HttpStatus.OK);
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
