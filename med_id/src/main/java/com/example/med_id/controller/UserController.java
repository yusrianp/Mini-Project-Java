package com.example.med_id.controller;

import com.example.med_id.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {

        String role =  authResult.getAuthorities().toString();

        if(role.contains("ROLE_ADMIN")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin"));
        }
        else if(role.contains("ROLE_DOKTER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/dokter"));
        }
        else if(role.contains("ROLE_PASIEN")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/pasien"));
        }
    }
}

