package com.hpprediction.demo.controllers;


import com.hpprediction.demo.payload.response.MessageResponse;
import com.hpprediction.demo.service.ResetPasswordService;
import com.hpprediction.demo.payload.request.ResetRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;

    @PostMapping(path = "resetPassword")
    public ResponseEntity<MessageResponse> resetPassword(@RequestParam("email") String email){
        MessageResponse response;

        try{
            response = resetPasswordService.resetPassword(email);
        }
        catch(IllegalStateException exception){
            return new ResponseEntity<>(new MessageResponse(exception.getMessage()), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping(path="changePassword")
    public ResponseEntity<MessageResponse> changePassword(@RequestBody ResetRequest request){
        MessageResponse response;
        try{
            response = resetPasswordService.changePassword(request);
        }
        catch(IllegalStateException illegalStateException){
            return new ResponseEntity<>(new MessageResponse(illegalStateException.getMessage()),
                    HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
