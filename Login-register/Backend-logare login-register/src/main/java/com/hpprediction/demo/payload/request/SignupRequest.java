package com.hpprediction.demo.payload.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class SignupRequest {
    private String username;
    private String name;
    private String phoneNumber;
    private String email;
    private Set<String> role;
    private String password;
}
