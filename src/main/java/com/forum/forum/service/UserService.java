package com.forum.forum.service;

import com.forum.forum.dao.TokenDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.EmailDto;
import com.forum.forum.dto.RegistrationDto;
import com.forum.forum.dto.UserOutputDto;
import com.forum.forum.model.Token;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, TokenDao tokenDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(RegistrationDto registrationDto){
        userDao.save(new User(registrationDto.getUsername(), registrationDto.getEmail(),passwordEncoder.encode(registrationDto.getPassword()), "USER"));
    }

    //TODO date to change
    public UserOutputDto getUserByUserName(String username){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsername(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not authorized."));
        return new UserOutputDto(user.getUsername(), user.getCreatedAt().toString(), user.getTopics().size(), user.getComments().size());
    }
}
