package com.hpprediction.demo.security;

import com.hpprediction.demo.userapp.UserDetailsImplem;
import com.hpprediction.demo.userapp.repositories.UserRepository;
import com.hpprediction.demo.userapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class OAuth2LoginSuccesHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();

        boolean userExists = userRepository.findByEmail(Encoder.encrypt(email))
                .isPresent();

        if (!userExists) {
            UserDetailsImplem user = new UserDetailsImplem();
            user.setPassword("-");
            user.setEmail(Encoder.encrypt(email));
            userService.createNewUserAfterOAuthLoginSucces(user);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
