package com.hpprediction.demo.service;

import com.hpprediction.demo.repository.PasswordResetRepository;
import com.hpprediction.demo.entity.PasswordResetToken;
import com.hpprediction.demo.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PasswordResetTokenService {

    private final PasswordResetRepository passwordResetRepository;

    public void saveConfirmationToken(PasswordResetToken token){
        passwordResetRepository.save(token);
    }

    public Optional<PasswordResetToken> getToken(String token) {
        return passwordResetRepository.findByToken(token);
    }

    public Optional<PasswordResetToken> getTokenByUser(User user){
        return passwordResetRepository.findByUser(user);
    }

    public void destroyToken(String token) {
        passwordResetRepository.destroyToken(token);
    }
}
