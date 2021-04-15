package com.hpprediction.demo.resetpassword.token;

import com.hpprediction.demo.UsersApp.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PasswordResetToken {

    private static final int MINUTES_TILL_EXPIRATION = 180;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    private LocalDateTime confirmedAt;

    private LocalDateTime expiryDate;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public PasswordResetToken(String token,
                              User user,
                              LocalDateTime creationDate,
                              LocalDateTime expiryDate) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }
}