package com.example.med_id.controller;

import com.example.med_id.model.Token;
import com.example.med_id.model.User;
import com.example.med_id.repositories.TokenRepo;
import com.example.med_id.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/token")
public class ApiTokenController {

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private com.example.med_id.controller.ApiUserController apiUserController;

    // Set token expired time
    private static final long OTPValidDuration = 10 * 60 * 1000;   // 10 minutes

    @GetMapping("/tokenverification/{token}")
    public ResponseEntity<Object> TokenVerification(@PathVariable("token") String token)
    {
        try
        {
            Optional<Token> tokenData = this.tokenRepo.FindActiveToken(token);

            if (tokenData.isPresent()) {

                // Get current time in milis
                Long currentDateTimeInMilis = System.currentTimeMillis();

                // Get value expiredOn
                Date expired = tokenData.get().getExpiredOn();

                // Get token expired in milis
                Long tokenExpiredtDateTimeInMilis = expired.getTime();

                // Get difference current & expired
                Long difference = tokenExpiredtDateTimeInMilis - currentDateTimeInMilis;

                // Convert difference to minutes
                Long differenceInMinutes = TimeUnit.MILLISECONDS.toMinutes(difference);

                if (differenceInMinutes >= 0 && differenceInMinutes <= 10) {

                    Map<String, Object> response = new HashMap<>();
                    response.put("msg", "Success");
                    response.put("userId", tokenData.get().getUserId());

                    return new ResponseEntity<>(response, HttpStatus.OK);
                }

                Map<String, Object> response = new HashMap<>();
                response.put("msg", "Error");
                response.put("body", "Token expirred");

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("msg", "Error");
            response.put("body", "Token not valid");

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }

        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/changetokenstatus/{id}")
    public ResponseEntity<Map<String, Object>> PatchToken(@RequestBody Token token, User userData, @PathVariable("id") Long id)
    {

        try {
            Optional<Token> tokenData = this.tokenRepo.findById(id);

            // Unlock user
            userData.setLocked(false);
            userData.setEmail(tokenData.get().user.getEmail());

            this.userRepo.save(userData);

            // Lock token
            token.setId(id);
            token.setCreatedOn(tokenData.get().getCreatedOn());
            token.setCreatedBy(tokenData.get().getUserId());
            token.setModifiedOn(new Date());
            token.setModifiedBy(tokenData.get().getUserId());
            token.setExpiredOn(tokenData.get().getExpiredOn());
            token.setUserId(tokenData.get().getUserId());
            token.setEmail(tokenData.get().getEmail());
            token.setExpired(token.getExpired());
            token.setToken(tokenData.get().getToken());
            token.setUsedFor(tokenData.get().getUsedFor());
            token.setDelete(token.getDelete());

            this.tokenRepo.save(token);

            Map<String, Object> response = new HashMap<>();
            response.put("msg", "Success");
            response.put("userId", tokenData.get().getUserId());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/requestnewtoken/{id}")
    public ResponseEntity<Object> GenerateNewToken(@PathVariable("id") Long id, Token token)
    {
        try
        {
            Optional<Token> tokenData = this.tokenRepo.FindTokenByUserId(id);

            if (tokenData.get().getExpired() == false)
            {
                // Get timestamp
                Long currentDateTimeInMilis = System.currentTimeMillis();
                // Calculate token expired
                Long expiredOTPOn = currentDateTimeInMilis + OTPValidDuration;
                // Get new token
                String newToken = this.apiUserController.GenerateToken();

                token.setId(id);
                token.setCreatedOn(tokenData.get().getCreatedOn());
                token.setCreatedBy(tokenData.get().getCreatedBy());
                token.setModifiedBy(tokenData.get().getUserId());
                token.setModifiedOn(new Date());
                token.setDelete(false);
                token.setEmail(tokenData.get().getEmail());
                token.setExpiredOn(new Date(expiredOTPOn));
                token.setExpired(false);
                token.setToken(newToken);
                token.setUsedFor("User verification");
                token.setUserId(tokenData.get().getUserId());

                this.tokenRepo.save(token);

                this.apiUserController.sendMail(tokenData.get().getEmail(), newToken);

                return new ResponseEntity<>("Silahkan cek email anda", HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        catch (Exception e)
        {
            return new ResponseEntity<>("Token sudah pernah digunakan, silahkan login ke akun anda", HttpStatus.BAD_REQUEST);
        }
    }
}
