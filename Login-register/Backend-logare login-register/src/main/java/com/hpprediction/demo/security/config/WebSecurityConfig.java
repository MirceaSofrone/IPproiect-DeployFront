package com.hpprediction.demo.security.config;

import com.hpprediction.demo.userapp.services.UserService;
import com.hpprediction.demo.security.CustomOAuth2Service;
import com.hpprediction.demo.security.OAuth2LoginSuccesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OAuth2LoginSuccesHandler oAuth2LoginSuccesHandler;

    @Autowired
    private CustomOAuth2Service oAuth2Service;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/login", "/api/auth/**", "/api/v1/confirm", "/css/**", "api/v1/feedback").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .anyRequest().authenticated()
        .and()
                .formLogin().and()
                .oauth2Login()
                .userInfoEndpoint().userService(oAuth2Service)
                        .and()
                        .successHandler(oAuth2LoginSuccesHandler);
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/v*/**", "/css/**")
//                .permitAll()
//                .anyRequest().permitAll()
//
//
//
//
//
//
//
//
//                ce)
//
//




}

