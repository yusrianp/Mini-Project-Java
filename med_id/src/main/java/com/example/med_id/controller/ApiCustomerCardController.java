package com.example.med_id.controller;

import com.example.med_id.model.Customer;
import com.example.med_id.model.CustomerCard;
import com.example.med_id.repositories.CustomerCardRepo;
import com.example.med_id.repositories.CustomerRepo;
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
public class ApiCustomerCardController {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerCardRepo customerCardRepo;


    @GetMapping("/customercard")
    public ResponseEntity<Object> GetAllCustomerCard()
    {
        try {
            List<CustomerCard> customerCards = this.customerCardRepo.findAll();
            return new ResponseEntity<>(customerCards, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/customercard")
    public ResponseEntity<Object> SaveCustomerRegisteredCard(@RequestBody CustomerCard customerCard)
    {
//        CustomerRegisteredCard customerRegisteredCardData = this.customerRegisteredCardRepo.save(customerRegisteredCard);
        try
        {
            customerCard.setCreatedOn(new Date());
            customerCard.setDelete(false);
            System.out.println(customerCard);
            this.customerCardRepo.save(customerCard);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("customercard/{id}")
    public ResponseEntity<List<CustomerCard>> GetCustomerCardById(@PathVariable("id") Long id)
    {
        try
        {
            Optional<Customer> customerBiodata = this.customerRepo.GetCustomerByBiodataId(id);
            if (customerBiodata.isPresent())
            {
                Optional<CustomerCard> customerCard = this.customerCardRepo.GetCustomerCardByCustomerID(customerBiodata.get().getId());
                System.out.println(customerCard);
                if (customerCard.isPresent()){
                    ResponseEntity responseEntity = new ResponseEntity<>(customerCard, HttpStatus.OK);
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
}
