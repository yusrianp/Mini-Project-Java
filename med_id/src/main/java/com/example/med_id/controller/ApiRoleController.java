package com.example.med_id.controller;

import com.example.med_id.model.Role;
import com.example.med_id.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class ApiRoleController {

    @Autowired
    private RoleRepo roleRepo;

    //  Menampilkan data
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> GetAllRole(){
        try {
            List<Role> role = this.roleRepo.GetAvailable();
            return new ResponseEntity<>(role, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //  Open form & Save data
    @PostMapping("/roles")
    public ResponseEntity<Object> SaveRole(@RequestBody Role role){
        try {
            this.roleRepo.save(role);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>("faild",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/searchrole/{keyword}")
    public ResponseEntity<List<Role>> SearchRoleName(@PathVariable("keyword") String keyword)
    {
        if (keyword != null)
        {
            List<Role> role = this.roleRepo.SearchRole(keyword);
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            List<Role> role = this.roleRepo.GetAvailable();
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
    }

    @GetMapping("/rolemapped")
    public ResponseEntity<Map<String, Object>> GetAllPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
    {
        try {
            List<Role> role = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page, size);

            Page<Role> pageTuts;

            pageTuts = roleRepo.GetAvailablePage(pagingSort);

            role = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("role", role);
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
