package com.example.med_id.controller;

import com.example.med_id.model.Customer;
import com.example.med_id.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class ApiCustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> GetAllCustomer()
    {
        try {
            List<Customer> customers = this.customerRepo.findAll();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
