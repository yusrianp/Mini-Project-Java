package com.example.med_id.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/buatjanji/")
public class AppointmentController {
    @GetMapping(value="index")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("buatjanji/index");
        return view;
    }
}
