package com.hpprediction.demo.security;

import com.hpprediction.demo.UsersApp.services.UserService;
import com.hpprediction.demo.resetpassword.token.PasswordResetToken;
import com.hpprediction.demo.resetpassword.token.PasswordResetTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SecurityService {
    private final PasswordResetTokenService passwordResetTokenService;
    private final UserService userService;

    public boolean isPasswordTokenValid(PasswordResetToken token){
        return token.getExpiryDate().isAfter(LocalDateTime.now());
    }

    public boolean isPasswordTokenValid(String token){

       PasswordResetToken passwordResetToken;

        if(passwordResetTokenService.getToken(token).isPresent()){
            passwordResetToken = passwordResetTokenService.getToken(token).get();

            if(passwordResetToken.getExpiryDate().isAfter(LocalDateTime.now())){
                return true;
            }
        }
        return false;
    }
}
