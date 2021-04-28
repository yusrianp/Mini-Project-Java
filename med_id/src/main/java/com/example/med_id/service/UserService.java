package com.example.med_id.service;

import com.example.med_id.model.User;
import com.example.med_id.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    private User user;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User optionalUser = userRepo.FindEmail(email);

        if (optionalUser != null)
        {
            Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
            grantedAuthoritySet.add(new SimpleGrantedAuthority(optionalUser.role.getCode()));

            UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(optionalUser.getEmail(), optionalUser.getPassword(), grantedAuthoritySet );
            return userDetails;
        }

        System.out.println("User not found! " + email);
        throw new UsernameNotFoundException("User " + email + " was not found in the database");
    }

    public String getEmail()
    {
        return this.user.getEmail();
    }
}