package com.hpprediction.demo.registration;

import com.hpprediction.demo.UsersApp.User;
import com.hpprediction.demo.UsersApp.UserRoleEnum;
import com.hpprediction.demo.UsersApp.services.UserService;
import com.hpprediction.demo.registration.token.ConfirmationToken;
import com.hpprediction.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Email invalid");
        }

        return userService.signUpUser(
                new User(request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRoleEnum.USER
                ));
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("NU s-a gasit token-ul"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email-ul este confirmat deja");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Tokenul a expirat");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(
                confirmationToken.getUser().getEmail());
        return "Confirmat";
    }
}
