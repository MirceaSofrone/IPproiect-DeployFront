package com.hpprediction.demo.login;

import com.hpprediction.demo.userapp.UserDetailsImplem;
import com.hpprediction.demo.payload.request.LoginRequest;
import com.hpprediction.demo.payload.response.JwtResponse;
import com.hpprediction.demo.security.Encoder;
import com.hpprediction.demo.security.config.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public JwtResponse signInUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(Encoder.encrypt(loginRequest.getUsername()),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImplem userDetails = (UserDetailsImplem) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), Encoder.decrypt(userDetails.getUsername()),
                Encoder.decrypt(userDetails.getEmail()), roles);
    }
}
