package com.forum.forum.service;

import com.forum.forum.dao.TokenDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.EmailDto;
import com.forum.forum.dto.ResetDto;
import com.forum.forum.model.Token;
import com.forum.forum.model.User;
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
        Optional<Token> tokenOptional = Optional.ofNullable(tokenDao.findByToken(token));
        Token resetToken = tokenOptional.orElseThrow(() -> new RuntimeException("Token not found"));
        Optional<User> userOptional = Optional.ofNullable(userDao.findByEmail(resetToken.getUser().getEmail()));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(resetDto.getPassword()));
        user.setToken(null);
        tokenDao.delete(resetToken);
        userDao.save(user);
    }

    public void sendToken(EmailDto emailDto){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByEmail(emailDto.getEmail()));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(user.getToken() == null){
            Token token = new Token(UUID.randomUUID(), user);
            user.setToken(token);
            tokenDao.save(token);
        }
    }

    public void sendEmailWithReset(UUID token){
        //TODO
    }
}
