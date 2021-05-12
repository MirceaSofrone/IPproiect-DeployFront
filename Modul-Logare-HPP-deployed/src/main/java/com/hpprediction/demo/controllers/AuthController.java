package com.hpprediction.demo.controllers;

import com.hpprediction.demo.payload.response.JwtResponse;

import com.hpprediction.demo.service.LoginService;
import com.hpprediction.demo.payload.request.LoginRequest;
import com.hpprediction.demo.payload.request.SignupRequest;
import com.hpprediction.demo.payload.response.MessageResponse;
import com.hpprediction.demo.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;


    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return new ResponseEntity<>(loginService.signInUser(loginRequest), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        MessageResponse response;

        try {
            response = registrationService.registerUser(signUpRequest);
        } catch (IllegalStateException exception) {
            return new ResponseEntity<>(new MessageResponse(exception.getMessage()), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<MessageResponse> confirm(@RequestParam("token") String token) {
        MessageResponse response;

        try {
            response = registrationService.confirmToken(token);
        } catch (IllegalStateException exception) {
            return new ResponseEntity<>(new MessageResponse(exception.getMessage()), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}