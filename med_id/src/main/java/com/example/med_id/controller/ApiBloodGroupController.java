package com.example.med_id.controller;
import com.example.med_id.model.BloodGroup;
import com.example.med_id.repositories.BloodGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiBloodGroupController {
    @Autowired
    private BloodGroupRepo bloodGroupRepo;

    @GetMapping("/bloodGroup")
    public ResponseEntity<List<BloodGroup>> GetAllBloodGroup(){
        try{
            List<BloodGroup> bloodGroup = this.bloodGroupRepo.findAll();
            return new ResponseEntity<>( bloodGroup, HttpStatus.OK);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/bloodGroup")
    public ResponseEntity<Object> SaveBloodGroup(@RequestBody BloodGroup bloodGroup){
//        BloodGroup bloodGroupData = this.bloodGroupRepo.save(bloodGroup);
        try
        {

            bloodGroup.setCreatedOn(new Date());
            bloodGroup.setCreatedBy(1L);
            this.bloodGroupRepo.save(bloodGroup);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch
        (Exception exception){
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bloodGroup/{id}")
    public ResponseEntity<List<BloodGroup>> GetBloodGroupById(@PathVariable("id") Long id){
        try {
            Optional<BloodGroup> bloodGroup = this.bloodGroupRepo.findById(id);
            if (bloodGroup.isPresent()) {
                ResponseEntity rest = new ResponseEntity<>(bloodGroup, HttpStatus.OK);
                return rest;
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

//    @GetMapping("bloodGroupmapped")
//    public ResponseEntity<Map<String, Object>> GetAllpage(@RequestParam(defaultValue = "0")int page,
//                                                          @RequestParam(defaultValue = "5")int size)
//    {
//        try {
//            List<BloodGroup> bloodGroup = new ArrayList<>();
//            Pageable pagingSort = PageRequest.of(page, size);
//            Page<BloodGroup> pageTuts;
//            pageTuts = bloodGroupRepo.findAll(pagingSort);
//            bloodGroup = pageTuts.getContent();
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("bloodGroup", bloodGroup);
//            response.put("currentPage", pageTuts.getNumber());
//            response.put("totalItems", pageTuts.getTotalElements());
//            response.put("totalPages", pageTuts.getTotalPages());
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PutMapping("/bloodGroup/{id}")
    public ResponseEntity<Object> UpdateBloodGroup(@RequestBody BloodGroup bloodGroup, @PathVariable("id") Long id) {
        Optional<BloodGroup> bloodGroupData = this.bloodGroupRepo.findById(id);
        if (bloodGroupData.isPresent()) {
            bloodGroup.setId(id);
            bloodGroup.setModifiedOn(new Date());
            bloodGroup.setModifiedBy(1L);

            this.bloodGroupRepo.save(bloodGroup);

            ResponseEntity rest = new ResponseEntity<>("Success", HttpStatus.OK);
            return rest;
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/deletebloodGroup/{id}")
    public ResponseEntity<Object> DeleteDua( @RequestBody BloodGroup bloodGroup,
                                                      @PathVariable("id") Long id){
        Optional<BloodGroup> bloodGroupData = this.bloodGroupRepo.findById(id);
        if (bloodGroupData.isPresent()){
            bloodGroup.setId(id);
            bloodGroup.setDeletedBy(1L);
            bloodGroup.setDeletedOn(new Date());

            this.bloodGroupRepo.save(bloodGroup);

            ResponseEntity responseEntity = new ResponseEntity<>("Success", HttpStatus.OK);
            return responseEntity;
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/deletebloodGroup/{id}")
    public ResponseEntity<List<BloodGroup>> GetDeleteBloodGroupById(@PathVariable ("id") Long id){
        try{
            Optional<BloodGroup> bloodGroup = this.bloodGroupRepo.findById(id);
            if(bloodGroup.isPresent()){
                ResponseEntity responseEntity = new ResponseEntity<>( bloodGroup, HttpStatus.OK);
                return responseEntity;
            } else
                return ResponseEntity.notFound().build();
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/deletebloodGroupdua/{id}")
    public ResponseEntity<List<BloodGroup>> GetDeleteBloodGroupDuaById(@PathVariable("id") Long id)
    {
        try
        {
            Optional<BloodGroup> bloodGroup = this.bloodGroupRepo.findById(id);
            if (bloodGroup.isPresent())
            {
                ResponseEntity responseEntity = new ResponseEntity<>(bloodGroup, HttpStatus.OK);
                return responseEntity;
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


//    //Search
//    @GetMapping("searchbloodGroup/{jenis}")
//    public ResponseEntity<List<BloodGroup>> SearchBloodGroupByName(@PathVariable("jenis") String name){
//        if (name != null){
//            List<BloodGroup> bloodGroup = this.bloodGroupRepo.SearchBloodGroup(name);
//            return new ResponseEntity<>(bloodGroup, HttpStatus.OK);
//        } else {
//            List<BloodGroup> bloodGroup = this.bloodGroupRepo.findAll();
//            return new ResponseEntity<>(bloodGroup, HttpStatus.OK);
//        }
//    }

}
