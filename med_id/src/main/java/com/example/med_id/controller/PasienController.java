package com.example.med_id.controller;

import com.example.med_id.model.User;
import com.example.med_id.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/pasien")
public class PasienController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "")
    public ModelAndView index(Model model, Principal principal)
    {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        String email = loggedInUser.getName();

        Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

        String fullName = userDetail.get().biodata.getFullname();

        String[] firstName = fullName.split("\\s+");

        model.addAttribute("fullName", fullName);
        model.addAttribute("firstName", firstName[0]);

        ModelAndView view = new ModelAndView("pasien/index");
        return view;
    }
}
