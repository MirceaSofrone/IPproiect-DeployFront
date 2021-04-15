package com.hpprediction.demo.registration;

import com.sun.mail.iap.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.Registration;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping(path = "/registration")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request){
        String raspuns = "";

        try{
            raspuns = registrationService.register(request);
        }
        catch(IllegalStateException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(raspuns, HttpStatus.CREATED);
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<?> confirm(@RequestParam("token") String token) {
        String raspuns = "";

        try{
            raspuns = registrationService.confirmToken(token);
        }
        catch(IllegalStateException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(raspuns, HttpStatus.OK);
    }

}
