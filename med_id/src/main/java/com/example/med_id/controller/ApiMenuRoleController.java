package com.example.med_id.controller;

import com.example.med_id.model.MenuRole;
import com.example.med_id.repositories.MenuRoleRepo;
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
public class ApiMenuRoleController {
    @Autowired
    private MenuRoleRepo menuRoleRepo;
    //  Menampilkan data
    @GetMapping("/menurole")
    public ResponseEntity<List<MenuRole>> GetAllMenuRole(){
        try {
            List<MenuRole> menuRole = this.menuRoleRepo.GetAvailable();
            return new ResponseEntity<>(menuRole, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //  Open form & Save data
    @PostMapping("/menurole")
    public ResponseEntity<Object> SaveMenuRole(@RequestBody MenuRole menuRole){
        try {
            long a = 1 ;
            menuRole.setCreatedBy(a);
            menuRole.setCreatedOn(new Date());
            this.menuRoleRepo.save(menuRole);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>("faild",HttpStatus.BAD_REQUEST);
        }
    }

    //  Get By Id
    @GetMapping("/menurole/{id}")
    public ResponseEntity<List<MenuRole>> GetMenuRoleById(@PathVariable("id") Long id) {
        try{
            Optional<MenuRole> menuRole = this.menuRoleRepo.findById(id);
            if (menuRole.isPresent()) {
                ResponseEntity rest = new ResponseEntity<>(menuRole, HttpStatus.OK);
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

    @PutMapping("/menurole/{id}")
    public ResponseEntity<Object> UpdateMenuRole(@RequestBody MenuRole menuRole, @PathVariable("id") Long id)
    {
        Optional<MenuRole> menuRoleData = this.menuRoleRepo.findById(id);

        if (menuRoleData.isPresent())
        {
            long a = 1 ;
            menuRole.setId(id);
            menuRole.setDelete(true);
            menuRole.setDeletedBy(a);
            menuRole.setDeletedOn(new Date());
            this.menuRoleRepo.save(menuRole);
            ResponseEntity rest = new ResponseEntity<>("success", HttpStatus.OK);
            return rest;
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
