package com.example.med_id.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/relation/")
public class CustomerRelationController {
    @GetMapping(value="index")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("customer_relation/index");
        return view;
    }
}
