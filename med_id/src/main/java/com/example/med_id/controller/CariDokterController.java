package com.example.med_id.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/cari_dokter")
public class CariDokterController {
    @GetMapping(value="")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("cari_dokter/index");
        return view;
    }
}
