package com.example.med_id.controller;

import com.example.med_id.model.Biodata;
import com.example.med_id.model.Customer;
import com.example.med_id.model.User;
import com.example.med_id.repositories.BiodataRepo;
import com.example.med_id.repositories.CustomerRepo;
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
@RequestMapping("/customercard")
public class CustomerCardController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private BiodataRepo biodataRepo;

    @GetMapping(value = "")
    public ModelAndView index(Model model)
    {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        String email = loggedInUser.getName();

        Optional<User> userDetail = this.userRepo.FindByEmail(email.toString());

        //m_user
        Long userIDPasien = userDetail.get().getId();
        String fullName = userDetail.get().biodata.getFullname();
        String[] firstName = fullName.split("\\s+");

        System.out.println("\nuser ID pasien: " + userIDPasien);
        System.out.println("fullName: " + fullName + "\n");

        //m_biodata
        Optional<Biodata> pasienDetail = this.biodataRepo.findById(userIDPasien);
        Long biodataIDPasien = pasienDetail.get().getId();//id biodata pasien dari table m_biodata

        System.out.println("\nbiodataIDPasien : " + biodataIDPasien + "\n");

        //m_customer
        Optional<Customer> customerDetail = this.customerRepo.GetCustomerByBiodataId(biodataIDPasien);
        Long customerIDPasien = customerDetail.get().getId();
        System.out.println("customerdetail: " + biodataIDPasien);
        System.out.println("\ncustomer ID Pasien: " + customerIDPasien + "\n");

        model.addAttribute("fullName", fullName);
        model.addAttribute("firstName", firstName[0]);
        model.addAttribute("biodataIDPasien", biodataIDPasien);
        model.addAttribute("customerIDPasien", customerIDPasien);
//
//
//        System.out.println("\npasienID: " + pasienID );
//        System.out.println("\nuserIdPasien: " + userIdPasien );

//    @GetMapping(value = "index")
//    public ModelAndView index() {
        ModelAndView view = new ModelAndView("customercard/index");
        return view;
    }
}
