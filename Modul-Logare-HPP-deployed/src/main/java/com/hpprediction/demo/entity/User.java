package com.hpprediction.demo.entity;

import com.hpprediction.demo.datamodels.AuthProviderEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String phoneNumber;

    @Size(max = 255)
    private String username;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private AuthProviderEnum provider;

    private String providerId;

    private boolean isEnabled;

    private boolean isLocked;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String name,
                String phoneNumber,
                String username,
                String email,
                String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String name,
                String phoneNumber,
                String username,
                String email,
                String password,
                boolean isEnabled,
                boolean isLocked,
                Set<Role> roles) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.isLocked = isLocked;
        this.roles = roles;
    }

}
