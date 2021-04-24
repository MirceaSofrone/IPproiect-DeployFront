package com.hpprediction.demo.resetpassword;


import com.hpprediction.demo.userapp.User;
import com.hpprediction.demo.userapp.services.UserService;
import com.hpprediction.demo.email.EmailService;
import com.hpprediction.demo.email.EmailTemplateService;
import com.hpprediction.demo.payload.response.MessageResponse;
import com.hpprediction.demo.resetpassword.token.PasswordResetToken;
import com.hpprediction.demo.resetpassword.token.PasswordResetTokenService;
import com.hpprediction.demo.security.Encoder;
import com.hpprediction.demo.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ResetPasswordService {

    private static final int MINUTES_TILL_EXPIRATION = 180;
    private static final String HOST = "localhost:8081";
    private final UserService userService;

    private final SecurityService securityService;
    private final PasswordResetTokenService passwordResetTokenService;
    private final EmailService emailService;

    public MessageResponse resetPassword(String email) {

        Optional<User> userRequestingReset;

        userRequestingReset = userService.loadAllUserData(Encoder.encrypt(email));

        if(!userRequestingReset.isPresent()){
            return new MessageResponse("You can't request a password reset (ERROR : ERR78)! Bad email!") ;
        }

        User requestingUser = userRequestingReset.get();

        Optional<PasswordResetToken> tokenResetPresent;

        tokenResetPresent = passwordResetTokenService.getTokenByUser(requestingUser);

        if(tokenResetPresent.isPresent()
                && securityService.isPasswordTokenValid(tokenResetPresent.get())){
                return new MessageResponse("You can't request a password reset. Please wait.");
        }


        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(
                token,
                requestingUser,
                LocalDateTime.now().plusMinutes(MINUTES_TILL_EXPIRATION)
        );

        String link = "http://"+ HOST +"/api/auth/changePassword?token=" + token;

        passwordResetTokenService.saveConfirmationToken(passwordResetToken);

        Map<String, Object> model = new HashMap<>();

        if(requestingUser.getName() == null){
            model.put("name", "");
        } else {
            model.put("name", Encoder.decrypt(requestingUser.getName()));
        }

        model.put("link", link);

        emailService.send(email,
                "Password Reset Link - HPP",
                EmailTemplateService.TEMPLATERESETPWR,
                model);

        return new MessageResponse("Password reset token email sent |"   + token);
    }

    public MessageResponse changePassword(ResetRequest request) {
        String token = request.getToken();
        String newPassword = request.getPassword();

        Optional<PasswordResetToken> tokenReset = passwordResetTokenService.getToken(token);

        if(tokenReset.isPresent()
                && securityService.isPasswordTokenValid(token)){

                User user = tokenReset.get().getUser();
                userService.changeUserPassword(user, newPassword);

                passwordResetTokenService.destroyToken(token);

                return new MessageResponse("Password changed successfully");

        }
        throw new IllegalStateException("Token doesn't exist or is expired!");
    }
}
