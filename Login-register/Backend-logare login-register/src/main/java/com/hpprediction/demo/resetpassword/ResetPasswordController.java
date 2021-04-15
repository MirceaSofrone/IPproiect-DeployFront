package com.hpprediction.demo.resetpassword;

import com.hpprediction.demo.UsersApp.User;
import com.hpprediction.demo.UsersApp.services.UserService;
import com.hpprediction.demo.registration.RegistrationRequest;
import com.hpprediction.demo.resetpassword.token.PasswordResetToken;
import com.hpprediction.demo.resetpassword.token.PasswordResetTokenService;
import com.hpprediction.demo.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class ResetPasswordController {

    private ResetPasswordService resetPasswordService;
    private final PasswordResetTokenService passwordResetTokenService;
    private final SecurityService securityService;
    private final UserService userService;

    @PostMapping(path = "resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String email){
        String raspuns = "";

        try{
            raspuns = resetPasswordService.resetPassword(email);
        }
        catch(IllegalStateException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(raspuns, HttpStatus.OK);
    }

    @GetMapping(path = "resetPassword")
    public String changePassword(){
        return "forgetPassword";
    }

    @PostMapping(path="changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ResetRequest request){
        String raspuns = "";
        try{
            raspuns = resetPasswordService.changePassword(request);
        }
        catch(IllegalStateException illegalStateException){
            return new ResponseEntity<>(illegalStateException.getMessage(),
                    HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(raspuns, HttpStatus.CREATED);
    }

    @GetMapping(path = "changePassword")
    public ModelAndView changePassword(@RequestParam("token") String token
            , RedirectAttributes redirectAttributes){

        boolean valid = securityService.isPasswordTokenValid(token);

        if(!valid) {
            return new ModelAndView( "redirect:/login.html");
        } else {
            redirectAttributes.addFlashAttribute("token", token);
            return new ModelAndView( "/updatePassword");
        }
    }

}
