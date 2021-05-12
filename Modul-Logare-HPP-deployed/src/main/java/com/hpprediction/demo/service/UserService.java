package com.hpprediction.demo.service;

import com.hpprediction.demo.entity.User;
import com.hpprediction.demo.userapp.UserDetailsImplem;
import com.hpprediction.demo.repository.UserRepository;
import com.hpprediction.demo.entity.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static final long MINUTES_TILL_EXPIRATION = 180;
    private static final String USER_NOT_FOUND =
            "Couldn't FIND an user with this email (%s)!";

    private final UserRepository userRepository;

    private final ConfirmationTokenService confirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));

        return UserDetailsImplem.build(user);
    }

    public Optional<User> loadAllUserData(String email) {

        return userRepository.findByEmail(email);
    }


    public void changeUserPassword(User user, String newPassword) {

        String hashedPassword = bCryptPasswordEncoder.encode(newPassword);
        userRepository.modifyPassword(user.getEmail(), hashedPassword);
    }

    public void createNewUserAfterOAuthLoginSucces(UserDetailsImplem user) {

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(MINUTES_TILL_EXPIRATION),
                new User(user.getUsername(), user.getEmail(), user.getPassword())
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        confirmationTokenService.setConfirmedAt(token);
    }

    public void enableAppUser(String email) {
        userRepository.enableAppUser(email);
    }
}
