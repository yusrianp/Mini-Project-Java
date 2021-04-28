package com.example.med_id.controller;

import com.example.med_id.model.User;
import com.example.med_id.repositories.CustomerMemberRepo;
import com.example.med_id.model.CustomerMember;
import com.example.med_id.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiCustomerMemberController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomerMemberRepo customerMemberRepo;

    //  Menampilkan data
    @GetMapping("/customer-member")
    public ResponseEntity<List<CustomerMember>> GetAllCustomerRelation(){
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());
            
            List<CustomerMember> customerMember = this.customerMemberRepo.GetAvailable(userDetail.get().getBiodataId());
            return new ResponseEntity<>(customerMember, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
