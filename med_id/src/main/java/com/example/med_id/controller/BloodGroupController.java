package com.example.med_id.controller;


import com.example.med_id.repositories.BloodGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/bloodGroup")
public class BloodGroupController {

    @Autowired
    private BloodGroupRepo bloodGroupRepo;

    @GetMapping(value="index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("bloodGroup/index");
        return view;
    }

}
