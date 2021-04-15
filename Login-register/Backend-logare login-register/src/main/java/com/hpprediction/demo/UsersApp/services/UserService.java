package com.hpprediction.demo.UsersApp.services;

import com.hpprediction.demo.UsersApp.User;
import com.hpprediction.demo.UsersApp.repositories.UserRepository;
import com.hpprediction.demo.registration.token.ConfirmationToken;
import com.hpprediction.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    public static final int MINUTES_TILL_EXPIRATION = 180;

    private final String USER_NOT_FOUND =
            "Couldn't FIND an user with this email (%s)!";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                            new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(User user){
        boolean userExists = userRepository.findByEmail(user.getEmail())
        .isPresent();

        if(userExists){
            throw new IllegalStateException("Email luat deja!");
        }

        String encodedUserPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedUserPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(MINUTES_TILL_EXPIRATION),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public void changeUserPassword(User user, String newPassword){

        String hashedPassword = bCryptPasswordEncoder.encode(newPassword);
        userRepository.modifyPassword(user.getEmail(), hashedPassword);
    }

    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
}
