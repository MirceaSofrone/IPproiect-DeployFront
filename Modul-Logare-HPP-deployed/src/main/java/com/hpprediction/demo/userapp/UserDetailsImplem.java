package com.hpprediction.demo.userapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hpprediction.demo.entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserDetailsImplem implements UserDetails, OAuth2User {

    private static final long serialVersionUID = 1L;
    private long id;
    private String userName;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isEnabled;
    private boolean isLocked ;
    private Map<String, Object> attributes;


    public UserDetailsImplem(Long id,
                             String userName,
                             String email,
                             String password,
                             Collection<? extends GrantedAuthority> authorities,
                             boolean isEnabled,
                             boolean isLocked) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.isEnabled = isEnabled;
        this.isLocked = isLocked;
    }



    public static UserDetailsImplem build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImplem(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.isEnabled(),
                user.isLocked()
        );
    }

    public static OAuth2User build(User user,
                                   Map<String, Object> attributes) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        UserDetailsImplem userDetailsImplem =  new UserDetailsImplem(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.isEnabled(),
                user.isLocked());

        userDetailsImplem.setAttributes(attributes);

        return userDetailsImplem;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}
