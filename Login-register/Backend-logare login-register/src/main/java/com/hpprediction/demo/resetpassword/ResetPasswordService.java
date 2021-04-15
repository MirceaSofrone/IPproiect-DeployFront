package com.hpprediction.demo.resetpassword;


import com.hpprediction.demo.UsersApp.User;
import com.hpprediction.demo.UsersApp.services.UserService;
import com.hpprediction.demo.email.EmailSender;
import com.hpprediction.demo.email.EmailService;
import com.hpprediction.demo.registration.EmailValidator;
import com.hpprediction.demo.resetpassword.token.PasswordResetToken;
import com.hpprediction.demo.resetpassword.token.PasswordResetTokenService;
import com.hpprediction.demo.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ResetPasswordService {

    private static final int MINUTES_TILL_EXPIRATION = 180;
    private final UserService userService;
    private final EmailValidator emailValidator;
    private final EmailSender emailSender;

    private final SecurityService securityService;
    private final PasswordResetTokenService passwordResetTokenService;
    private final EmailService emailService;

    public String resetPassword(String email) {

        User userRequestingReset;

        try{
            userRequestingReset = (User) userService.loadUserByUsername(email);
        }
        catch(UsernameNotFoundException ue){
            return "Niciun user cu acel email!";
        }

        Optional<PasswordResetToken> tokenPrezentDeja;

        tokenPrezentDeja = passwordResetTokenService.getTokenByUser(userRequestingReset);

        if(tokenPrezentDeja.isPresent()){
            if(securityService.isPasswordTokenValid(tokenPrezentDeja.get())){
                return "Nu poti cere din nou resetarea parolei. Mai asteapta!";
            }
        }

        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(
                token,
                userRequestingReset,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(MINUTES_TILL_EXPIRATION)
        );

        String link = "http://localhost:8081/api/v1/resetPassword?token=" + token;

        passwordResetTokenService.saveConfirmationToken(passwordResetToken);

        emailService.send(email,
                buildResetPasswordEmail(userRequestingReset.getFirstName(), link));

        return "Password reset token: "  + token;
    }


    private String buildResetPasswordEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Here is a link to reset your password</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Please click on the below link to reset your account's password: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    public String changePassword(ResetRequest request) {
        String token = request.getToken();
        String newPassword = request.getPassword();

        Optional<PasswordResetToken> tokenResetare = passwordResetTokenService.getToken(token);

        if(tokenResetare.isPresent()){
            if(securityService.isPasswordTokenValid(token)){

                User user = tokenResetare.get().getUser();
                userService.changeUserPassword(user, newPassword);

                passwordResetTokenService.destroyToken(token);

                return "Parola schimbata cu success";
            }
        }
        throw new IllegalStateException("Tokenul nu exista sau e expirat!");
    }
}
