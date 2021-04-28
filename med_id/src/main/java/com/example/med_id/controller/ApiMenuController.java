package com.example.med_id.controller;

import com.example.med_id.repositories.MenuRepo;
import com.example.med_id.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiMenuController {
    @Autowired
    private MenuRepo menuRepo;

    //  Menampilkan data
    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> GetAllMenu(){
        try {
            List<Menu> menu = this.menuRepo.GetAvailable();
            return new ResponseEntity<>(menu, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
