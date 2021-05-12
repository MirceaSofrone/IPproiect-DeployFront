package com.hpprediction.demo.service;

import com.hpprediction.demo.entity.PasswordResetToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityService {
    private final PasswordResetTokenService passwordResetTokenService;

    public boolean isPasswordTokenValid(PasswordResetToken token){
        return token.getExpiryDate().isAfter(LocalDateTime.now());
    }

    public boolean isPasswordTokenValid(String token){

       PasswordResetToken passwordResetToken;
        Optional<PasswordResetToken> passwordToken=passwordResetTokenService.getToken(token);

        if(passwordToken.isPresent()){
            passwordResetToken =passwordToken.get();

            return passwordResetToken.getExpiryDate().isAfter(LocalDateTime.now());
        }
        return false;
    }
}
