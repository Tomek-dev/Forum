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
public class Token {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private LocalDateTime expiryDate;

    private Token() {
    }

    public static class Builder{
        private UUID token;
        private User user;

        public Builder token(UUID token){
            this.token = token;
            return this;
        }

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public Token build(){
            Token token = new Token();
            token.token = this.token;
            token.user = this.user;
            token.expiryDate = LocalDateTime.now().plusDays(1);
            return token;
        }
    }
}
