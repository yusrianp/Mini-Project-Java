package com.example.med_id.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/tabalamat/")
public class TabAlamatController {
    @GetMapping(value="index")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("tab_alamat/index");
        return view;
    }
}
