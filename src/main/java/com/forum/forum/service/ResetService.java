package com.forum.forum.service;

import com.forum.forum.dao.TokenDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.EmailDto;
import com.forum.forum.dto.ResetDto;
import com.forum.forum.model.Token;
import com.forum.forum.model.User;
import com.forum.forum.other.exceptions.TokenNotFoundException;
import com.forum.forum.other.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ResetService {

    private UserDao userDao;
    private TokenDao tokenDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ResetService(UserDao userDao, TokenDao tokenDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.tokenDao = tokenDao;
        this.passwordEncoder=passwordEncoder;
    }

    public void resetPassword(UUID token, ResetDto resetDto) {
        Optional<Token> tokenOptional = tokenDao.findByToken(token);
        Token resetToken = tokenOptional.orElseThrow(TokenNotFoundException::new);
        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(resetDto.getPassword()));
        user.setToken(null);
        tokenDao.delete(resetToken);
        userDao.save(user);
    }

    public void createToken(EmailDto emailDto){
        Optional<User> userOptional = userDao.findByEmail(emailDto.getEmail());
        User user = userOptional.orElseThrow(UserNotFoundException::new);
        if(user.getToken() == null){
            Token token = new Token.Builder()
                    .token(UUID.randomUUID())
                    .user(user)
                    .build();
            tokenDao.save(token);
        }
    }
}
