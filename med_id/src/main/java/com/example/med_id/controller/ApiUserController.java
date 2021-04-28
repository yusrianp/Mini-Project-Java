package com.example.med_id.controller;

import com.example.med_id.model.Biodata;
import com.example.med_id.model.Token;
import com.example.med_id.model.User;
import com.example.med_id.repositories.BiodataRepo;
import com.example.med_id.repositories.TokenRepo;
import com.example.med_id.repositories.UserRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
public class ApiUserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private BiodataRepo biodataRepo;

    // Set token expired time
    private static final long OTPValidDuration = 10 * 60 * 1000;   // 10 minutes

    @PostMapping("/checkemail")
    public ResponseEntity<Object> CheckEmail(@RequestBody User user, Token token, Biodata biodata, HttpServletRequest request)
    {

        Long userId;
        Optional<User> userEmail = this.userRepo.FindByEmail(user.getEmail());

        if (userEmail.isPresent())
        {
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "Gagal");
            response.put("body", "Email sudah digunakan");

            return new ResponseEntity<>(response, HttpStatus.FOUND);
        } else {
            Optional<User> userData = this.userRepo.findLastId();

            if (userData != null)
            {
                userId = userData.get().getId() + 1L;
            } else {
                userId = 1L;
            }

            // Generate token
            String getToken = GenerateToken();
            // Get timestamp
            Long currentDateTimeMilis = System.currentTimeMillis();
            // Calculate token expired
            Long expiredToken = currentDateTimeMilis + OTPValidDuration;

            HttpSession session = request.getSession();
            session.setAttribute("createdOn", new Date());
            session.setAttribute("createdBy", userId);
            session.setAttribute("email", user.getEmail());
            session.setAttribute("userId", userId);
            session.setAttribute("lockAccount", true);
            session.setAttribute("token", getToken);
            session.setAttribute("tokenExpiredOn", new Date(expiredToken));
            session.setAttribute("expired", false);

//            user.setEmail(user.getEmail());
//            user.setLocked(true);
//            this.userRepo.save(user);
//            // Get user id after save email
//            Long userId = user.getId();
//
//            biodata.setId(userId);
//            biodata.setCreatedOn(new Date());
//            biodata.setCreatedBy(userId);
//
//            this.biodataRepo.save(biodata);
//
//            // Generate token
//            String getToken = GenerateToken();
//            // Get timestamp
//            Long currentDateTimeInMilis = System.currentTimeMillis();
//            // Calculate token expired
//            Long expiredOTOOn = currentDateTimeInMilis + OTPValidDuration;
//
//            token.setEmail(user.getEmail());
//            token.setUsedFor("User verification");
//            token.setUserId(userId);
//            token.setToken(getToken);
//            token.setCreatedBy(userId);
//            token.setCreatedOn(new Date(currentDateTimeInMilis));
//            token.setExpiredOn(new Date(expiredOTOOn));
//            token.setExpired(false);
//
//            this.tokenRepo.save(token);
//
            sendMail(user.getEmail(), getToken);

            Map<String, Object> response = new HashMap<>();
            response.put("msg", "Success");
            response.put("userId", userId);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public String GenerateToken()
    {
        String token = RandomString.make(50);

        return token;
    }

    void sendMail(String userEmail, String token)
    {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(userEmail);
        msg.setFrom("support@med.id");
        msg.setSubject("Email verification");
        msg.setText("Hello, this is your Token: " + token + " Token will expired in 10 minutes.");

        javaMailSender.send(msg);
    }

    @PutMapping("/createpassword/{id}")
    public ResponseEntity<Map<String, Object>> CreatePassword(@RequestBody User user, @PathVariable("id") Long id)
    {
        try {
            Optional<User> userData = this.userRepo.findById(id);

            if (userData.get().getLocked() == false)
            {
                // Encrypted password
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
                String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());

                user.setId(id);
                user.setBiodataId(id);
                user.setEmail(userData.get().getEmail());
                user.setLocked(userData.get().getLocked());
                user.setPassword(encodePassword);

                this.userRepo.save(user);

                Map<String, Object> response = new HashMap<>();
                response.put("msg", "Success");
                response.put("userId", user.getId());

                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("msg", "account locked");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/createuserbiodata/{id}")
    public ResponseEntity<Object> CreateUserBiodata(@RequestBody Biodata biodata, @PathVariable("id") Long id)
    {
        try {
            Optional<Biodata> biodataData = this.biodataRepo.findById(id);

            if (biodataData.isPresent())
            {
                biodata.setId(id);
                biodata.setCreatedBy(biodataData.get().getCreatedBy());
                biodata.setCreatedOn(biodataData.get().getCreatedOn());
                biodata.setModifiedBy(id);
                biodata.setModifiedOn(new Date());
                biodata.setFullname(biodata.getFullname());
                biodata.setMobilePhone(biodata.getMobilePhone());

                this.biodataRepo.save(biodata);

                return new ResponseEntity<>("Success", HttpStatus.CREATED);
            }

            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/addroleuser/{id}")
    public ResponseEntity<Object> AddRoleUser(@RequestBody User user, @PathVariable("id") Long id)
    {
        try {
            Optional<User> userData = this.userRepo.findById(id);

            if (userData.isPresent())
            {

                user.setId(id);
                user.setBiodataId(id);
                user.setEmail(userData.get().getEmail());
                user.setLocked(userData.get().getLocked());
                user.setPassword(userData.get().getPassword());
                user.setRoleId(user.getRoleId());

                this.userRepo.save(user);

                return new ResponseEntity<>("Success", HttpStatus.CREATED);
            }

            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> Login(@RequestBody User user)
    {
        try
        {
            Optional<User> userData = this.userRepo.FindByEmail(user.getEmail());

            if (userData.isPresent())
            {
                if (userData.get().getLocked() == false)
                {
                    // Compare password
                    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());

                    boolean result = bCryptPasswordEncoder.matches(user.getPassword(), userData.get().getPassword());

                    if (result == true)
                    {


                        Map<String, Object> response = new HashMap<>();
                        response.put("msg", "match");

                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }

                    else {
                        Map<String, Object> response = new HashMap<>();
                        response.put("msg", "Password tidak cocok");

                        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    }

                }

                Map<String, Object> response = new HashMap<>();
                response.put("msg", "Akun terkunci");

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("msg", "Email tidak ditemukan");

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        }

        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

}
