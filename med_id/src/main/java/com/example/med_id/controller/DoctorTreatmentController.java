package com.example.med_id.controller;

import com.example.med_id.model.Doctor;
import com.example.med_id.model.User;
import com.example.med_id.repositories.DoctorRepo;
import com.example.med_id.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping(value = "/doctortreatment")
public class DoctorTreatmentController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @GetMapping(value = "")
    public ModelAndView index(Model model)
    {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        String email = loggedInUser.getName();

        Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

        String fullName = userDetail.get().biodata.getFullname();
        Long userIdDoctor = userDetail.get().getId();

        String[] firstName = fullName.split("\\s+");
        //ambil doctor id table doctor
        Optional<Doctor> dataDOctor = this.doctorRepo.findById(userDetail.get().getBiodataId());

        Long doctorID = dataDOctor.get().getId();



        model.addAttribute("fullName", fullName);
        model.addAttribute("firstName", firstName[0]);
        model.addAttribute("doctorID", doctorID);
        model.addAttribute("userIdDoctor", userIdDoctor);

        System.out.println("\nfullName:" + fullName);
        System.out.println("\ndoctorID: " + doctorID );
        System.out.println("\nuserIdDoctor: " + userIdDoctor );

//    @GetMapping(value = "index")
//    public ModelAndView index() {
        ModelAndView view = new ModelAndView("doctortreatment/index");
        return view;
    }
}
