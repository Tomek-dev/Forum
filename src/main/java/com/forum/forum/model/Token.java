package com.forum.forum.model;

import com.forum.forum.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Token {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private LocalDateTime expiryDate;

    public Token(UUID token, User user) {
        this.token = token;
        this.user = user;
        expiryDate = LocalDateTime.now().plusDays(1);
    }

    public Token() {
        expiryDate = LocalDateTime.now().plusDays(1);
    }
}
