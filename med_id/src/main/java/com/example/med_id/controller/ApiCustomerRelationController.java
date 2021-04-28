package com.example.med_id.controller;

import com.example.med_id.model.CustomerRelation;
import com.example.med_id.repositories.CustomerRelationRepo;
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
public class ApiCustomerRelationController {
    @Autowired
    private CustomerRelationRepo customerRelationRepo;

    //  Menampilkan data
    @GetMapping("/relation")
    public ResponseEntity<List<CustomerRelation>> GetAllCustomerRelation(){
        try {
            List<CustomerRelation> relation = this.customerRelationRepo.GetAvailable();
            return new ResponseEntity<>(relation, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //  Open form & Save data
    @PostMapping("/relation")
    public ResponseEntity<Object> SaveCustomerRelation(@RequestBody CustomerRelation customerRelation){
        try {
            long a = 1 ;
            customerRelation.setCreatedBy(a);
            customerRelation.setCreatedOn(new Date());
            this.customerRelationRepo.save(customerRelation);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>("faild",HttpStatus.BAD_REQUEST);
        }
    }

    //  mengedit data(Edit)
    @GetMapping("/relation/{id}")
    public ResponseEntity<List<CustomerRelation>> GetCustomerRelationById(@PathVariable("id") Long id) {
        try{
            Optional<CustomerRelation> customerRelation = this.customerRelationRepo.findById(id);
            if (customerRelation.isPresent()) {
                ResponseEntity rest = new ResponseEntity<>(customerRelation, HttpStatus.OK);
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

    @GetMapping("/searchrelation/{keyword}")
    public ResponseEntity<List<CustomerRelation>> SearchCustomerRelationName(@PathVariable("keyword") String keyword)
    {
        if (keyword != null)
        {
            List<CustomerRelation> customerRelation = this.customerRelationRepo.SearchRelation(keyword);
            return new ResponseEntity<>(customerRelation, HttpStatus.OK);
        } else {
            List<CustomerRelation> customerRelation = this.customerRelationRepo.GetAvailable();
            return new ResponseEntity<>(customerRelation, HttpStatus.OK);
        }
    }

    //  update data(Update)
    @PutMapping("/relation/{id}")
    public ResponseEntity<Object> UpdateCustomerRelation(@RequestBody CustomerRelation customerRelation, @PathVariable("id") Long id)
    {
        Optional<CustomerRelation> customerRelationData = this.customerRelationRepo.findById(id);

        if (customerRelationData.isPresent())
        {
            long a = 1 ;
            customerRelation.setId(id);
            customerRelation.setModifiedBy(a);
            customerRelation.setModifiedOn(new Date());
            this.customerRelationRepo.save(customerRelation);
            ResponseEntity rest = new ResponseEntity<>("success", HttpStatus.OK);
            return rest;
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/deleterelation/{id}")
    public ResponseEntity<Object> DeleteCustomerRelation(@RequestBody CustomerRelation customerRelation, @PathVariable("id") Long id)
    {
        Optional<CustomerRelation> customerRelationData = this.customerRelationRepo.findById(id);

        if (customerRelationData.isPresent())
        {
            long a = 1 ;
            customerRelation.setId(id);
            customerRelation.setDelete(true);
            customerRelation.setDeletedBy(a);
            customerRelation.setDeletedOn(new Date());
            this.customerRelationRepo.save(customerRelation);
            ResponseEntity rest = new ResponseEntity<>("success", HttpStatus.OK);
            return rest;
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
