package com.example.med_id.controller;
import com.example.med_id.model.*;
import com.example.med_id.repositories.BiodataAddressRepo;
import com.example.med_id.repositories.CustomerRepo;
import com.example.med_id.repositories.DoctorRepo;
import com.example.med_id.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiBiodataAddressController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BiodataAddressRepo biodataAddressRepo;

    //  Menampilkan data
    @GetMapping("/biodataaddress")
    public ResponseEntity<List<BiodataAddress>> GetAllBiodataAddress(){
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

            List<BiodataAddress> biodataAddress = this.biodataAddressRepo.GetAvailable(userDetail.get().getBiodataId());
            return new ResponseEntity<>(biodataAddress, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //  mengedit data(Edit)
    @GetMapping("/aaddress/{id}")
    public ResponseEntity<List<BiodataAddress>> GetBiodataAddressById(@PathVariable("id") Long id) {
        try{
            Optional<BiodataAddress> biodataAddress = this.biodataAddressRepo.findById(id);
            if (biodataAddress.isPresent()) {
                ResponseEntity rest = new ResponseEntity<>(biodataAddress, HttpStatus.OK);
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

    //  Open form & Save data
    @PostMapping("/biodataaddress")
    public ResponseEntity<Object> SaveBiodataAddress(@RequestBody BiodataAddress biodataAddress){
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

            long a = userDetail.get().getId() ;
            long b = userDetail.get().getBiodataId() ;
            biodataAddress.setCreatedBy(a);
            biodataAddress.setCreatedOn(new Date());
            biodataAddress.setBiodataId(b);
            this.biodataAddressRepo.save(biodataAddress);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>("faild",HttpStatus.BAD_REQUEST);
        }
    }



//    @GetMapping("/searchbio/{keyword}")
//    public ResponseEntity<List<BiodataAddress>> SearchBiodataAddressName(@PathVariable("keyword") String keyword)
//    {
//        if (keyword != null)
//        {
//            List<BiodataAddress> biodataAddress = this.biodataAddressRepo.SearchBiodataAddress(keyword);
//            return new ResponseEntity<>(biodataAddress, HttpStatus.OK);
//        } else {
//            List<BiodataAddress> biodataAddress = this.biodataAddressRepo.GetAvailable();
//            return new ResponseEntity<>(biodataAddress, HttpStatus.OK);
//        }
//    }

    @GetMapping("/searchbio")
    public ResponseEntity<Map<String, Object>> SearchBiodataAddressName(@RequestParam String keyword, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="5") int size){

        try {
            if (keyword != null) {

                Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
                String email = loggedInUser.getName();
                Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

                List<BiodataAddress> biodataAddressData = new ArrayList<>();

                Pageable paging = PageRequest.of(page, size, Sort.by("Label").ascending());

                Page<BiodataAddress> biodataAddress = this.biodataAddressRepo.SearchBiodataAddress(userDetail.get().getBiodataId(),keyword, paging);

                biodataAddressData = biodataAddress.getContent();

                Map<String, Object> response = new HashMap<>();
                response.put("biodataAddress", biodataAddressData);
                response.put("currentPage", biodataAddress.getNumber());
                response.put("totalItems", biodataAddress.getTotalElements());
                response.put("totalPages", biodataAddress.getTotalPages());

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
                String email = loggedInUser.getName();
                Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

                List<BiodataAddress> biodataAddress = this.biodataAddressRepo.GetAvailable(userDetail.get().getBiodataId());
                ResponseEntity la = new ResponseEntity<>(biodataAddress, HttpStatus.OK);
                return la;
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //  update data(Update)
    @PutMapping("/aaddress/{id}")
    public ResponseEntity<Object> UpdateBiodataAddress(@RequestBody BiodataAddress biodataAddress, @PathVariable("id") Long id)
    {
        Optional<BiodataAddress> biodataAddressData = this.biodataAddressRepo.findById(id);

        if (biodataAddressData.isPresent())
        {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

            long a = userDetail.get().getId() ;
            biodataAddress.setId(id);
            biodataAddress.setModifiedBy(a);
            biodataAddress.setModifiedOn(new Date());
            this.biodataAddressRepo.save(biodataAddress);
            ResponseEntity rest = new ResponseEntity<>("success", HttpStatus.OK);
            return rest;
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/delaaddress/{id}")
    public ResponseEntity<Object> DeleteBiodataAddress(@RequestBody BiodataAddress biodataAddress, @PathVariable("id") Long id)
    {
        Optional<BiodataAddress> biodataAddressData = this.biodataAddressRepo.findById(id);

        if (biodataAddressData.isPresent())
        {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

            long a = userDetail.get().getId() ;
            biodataAddress.setId(id);
            biodataAddress.setDelete(true);
            biodataAddress.setDeletedBy(a);
            biodataAddress.setDeletedOn(new Date());
            this.biodataAddressRepo.save(biodataAddress);
            ResponseEntity rest = new ResponseEntity<>("success", HttpStatus.OK);
            return rest;
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dressmapped")
    public ResponseEntity<Map<String, Object>> GetAllPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
    {
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());


            List<BiodataAddress> biodataAddress = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page, size);

            Page<BiodataAddress> pageTuts;

            pageTuts = biodataAddressRepo.GetAvailablePage(userDetail.get().getBiodataId(), pagingSort);

            biodataAddress = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("biodataAddress", biodataAddress);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dressmappeddesc")
    public ResponseEntity<Map<String, Object>> GetAllPageDesc(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
    {
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

            List<BiodataAddress> biodataAddress = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page, size);

            Page<BiodataAddress> pageTuts;

            pageTuts = biodataAddressRepo.GetAvailablePageDsc(userDetail.get().getBiodataId(), pagingSort);

            biodataAddress = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("biodataAddress", biodataAddress);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recipientmapped")
    public ResponseEntity<Map<String, Object>> GetAllRecipientPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
    {
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

            List<BiodataAddress> biodataAddress = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page, size);

            Page<BiodataAddress> pageTuts;

            pageTuts = biodataAddressRepo.GetAvailablePageRecipient(userDetail.get().getBiodataId(),pagingSort);

            biodataAddress = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("biodataAddress", biodataAddress);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recipientmappeddesc")
    public ResponseEntity<Map<String, Object>> GetAllRecipientPageDesc(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
    {
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

            List<BiodataAddress> biodataAddress = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page, size);

            Page<BiodataAddress> pageTuts;

            pageTuts = biodataAddressRepo.GetAvailablePageRecipientDsc(userDetail.get().getBiodataId(),pagingSort);

            biodataAddress = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("biodataAddress", biodataAddress);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/addressmapped")
    public ResponseEntity<Map<String, Object>> GetAllAddressPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
    {
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

            List<BiodataAddress> biodataAddress = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page, size);

            Page<BiodataAddress> pageTuts;

            pageTuts = biodataAddressRepo.GetAvailablePageAddress(userDetail.get().getBiodataId(), pagingSort);

            biodataAddress = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("biodataAddress", biodataAddress);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/addressmappeddesc")
    public ResponseEntity<Map<String, Object>> GetAllAddressPageDesc(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
    {
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String email = loggedInUser.getName();
            Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

            List<BiodataAddress> biodataAddress = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page, size);

            Page<BiodataAddress> pageTuts;

            pageTuts = biodataAddressRepo.GetAvailablePageAddressDsc(userDetail.get().getBiodataId(), pagingSort);

            biodataAddress = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("biodataAddress", biodataAddress);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
