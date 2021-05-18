package com.hpprediction.demo.service;


import com.hpprediction.demo.email.EmailValidator;
import com.hpprediction.demo.datamodels.AuthProviderEnum;
import com.hpprediction.demo.entity.Role;
import com.hpprediction.demo.entity.User;
import com.hpprediction.demo.datamodels.UserRoleEnum;
import com.hpprediction.demo.repository.RoleRepository;
import com.hpprediction.demo.repository.UserRepository;
import com.hpprediction.demo.repository.EmailSender;
import com.hpprediction.demo.email.EmailTemplate;
import com.hpprediction.demo.payload.request.SignupRequest;
import com.hpprediction.demo.payload.response.MessageResponse;
import com.hpprediction.demo.entity.ConfirmationToken;
import com.hpprediction.demo.security.encoder.Encoder;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class RegistrationService {

    public static final int MINUTES_TILL_EXPIRATION = 180;
    //private static final String confirmHost = "hpp-auth.herokuapp.com";
    private static final String confirmHost = "localhost:8081";
    private static final String ERROR_ROLES="Error: Role is not found.";
    private final UserService userService;
    private final EmailValidator emailValidator;
    private final EmailSender emailSender;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder hasher;
    private final ConfirmationTokenService confirmationTokenService;



    public MessageResponse registerUser(SignupRequest signUpRequest){

        if (userRepository.existsByUsername( Encoder.encrypt(signUpRequest.getUsername()))) {

            throw new IllegalStateException("Error: Username is already taken!");

        }

        if (userRepository.existsByEmail(Encoder.encrypt(signUpRequest.getEmail()))) {
            throw new IllegalStateException("Error: Email is already in use!");
        }

        boolean isValidEmail = emailValidator.test(signUpRequest.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Email invalid");
        }

        User user = new User(Encoder.encrypt(signUpRequest.getName()),
                Encoder.encrypt(signUpRequest.getPhoneNumber()),
                Encoder.encrypt(signUpRequest.getUsername()) ,
                Encoder.encrypt(signUpRequest.getEmail()),
                hasher.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(UserRoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ERROR_ROLES));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(UserRoleEnum.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ERROR_ROLES));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(UserRoleEnum.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException(ERROR_ROLES));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(UserRoleEnum.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(ERROR_ROLES));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        user.setEnabled(false);
        user.setLocked(false);
        user.setProvider(AuthProviderEnum.LOCAL);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(MINUTES_TILL_EXPIRATION),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        String link = "http://" + confirmHost + "/api/auth/confirm?token=" + token;

        Map<String, Object> model = new HashMap<>();

        if(user.getName() == null){
            model.put("name", "");
        } else {
            model.put("name", Encoder.decrypt(user.getName()));
        }

        model.put("link", link);


        emailSender.send(Encoder.decrypt(user.getEmail()),
                "Confirmation Link - HPP",
                EmailTemplate.TEMPLATECONFIRM,
                model);

        return new MessageResponse("User registered successfully!");
    }

    public MessageResponse confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("You need to specify a token for the confirmation"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("You've confirmed your account already");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(
                confirmationToken.getUser().getEmail());

        return new MessageResponse("Succesfully confirmed!");
    }

}
