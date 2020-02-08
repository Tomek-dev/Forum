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
        if(tokenDao.findByToken(token)==null){
            throw new RuntimeException("Token not found");
        }
        Token resetToken = tokenDao.findByToken(token);
        if(userDao.findByEmail(resetToken.getUser().getEmail())==null){
            throw new UsernameNotFoundException("User not found");
        }
        User user = userDao.findByEmail(resetToken.getUser().getEmail());
        user.setPassword(passwordEncoder.encode(resetDto.getPassword()));
        user.setToken(null);
        tokenDao.delete(tokenDao.findByToken(token));
        userDao.save(user);
    }

    public void sendToken(EmailDto emailDto){
        if(userDao.findByEmail(emailDto.getEmail())==null){
            throw new UsernameNotFoundException("There isn't that user");
        }
        User user = userDao.findByEmail(emailDto.getEmail());
        if(tokenDao.findByUser(user)==null){
            Token token = new Token(UUID.randomUUID(), user);
            user.setToken(token);
            tokenDao.save(token);
        }
    }

    public void sendEmailWithReset(UUID token){
        //TODO
    }
}
