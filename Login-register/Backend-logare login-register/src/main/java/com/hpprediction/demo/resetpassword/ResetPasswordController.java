package com.hpprediction.demo.resetpassword;


import com.hpprediction.demo.payload.response.MessageResponse;
import com.hpprediction.demo.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;

    private final SecurityService securityService;

    @PostMapping(path = "resetPassword")
    public ResponseEntity<MessageResponse> resetPassword(@RequestParam("email") String email){
        MessageResponse response;

        try{
            response = resetPasswordService.resetPassword(email);
        }
        catch(IllegalStateException exception){
            return new ResponseEntity<>(new MessageResponse(exception.getMessage()), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "resetPassword")
    public ModelAndView changePassword(){

        return new ModelAndView( "forgetPassword");
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

    @GetMapping(path = "changePassword")
    public ModelAndView changePassword(@RequestParam("token") String token
            , RedirectAttributes redirectAttributes){

        boolean valid = securityService.isPasswordTokenValid(token);

        if(!valid) {
            return new ModelAndView( "redirect:/api/v1/login");
        } else {
            redirectAttributes.addFlashAttribute("token", token);
            return new ModelAndView( "updatePassword.html");
        }
    }
}
