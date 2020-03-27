package com.forum.forum.service;

import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.MottoDto;
import com.forum.forum.dto.RegistrationDto;
import com.forum.forum.dto.UserInfoDto;
import com.forum.forum.dto.UserOutputDto;
import com.forum.forum.model.User;
import com.forum.forum.other.builder.UserBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {

    private static final ModelMapper MAPPER = new ModelMapper();


    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(RegistrationDto registrationDto){
        User user = UserBuilder.builder()
                .username(registrationDto.getUsername())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .roles(Collections.singleton("USER"))
                .createdAt(LocalDateTime.now())
                .build();
        userDao.save(user);
    }

    public UserOutputDto getUserByUsername(String username){
        Optional<User> userOptional = userDao.findByUsernameIgnoreCase(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not authorized."));
        return MAPPER.map(user, UserOutputDto.class);
    }

    @Transactional
    public void deleteUser(String username){
        userDao.deleteByUsernameIgnoreCase(username);
    }

    public void setMotto(String username, MottoDto mottoDto){
        Optional<User> userOptional = userDao.findByUsernameIgnoreCase(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setMotto(mottoDto.getMotto());
        userDao.save(user);
    }

    public UserInfoDto getInfo(String username){
        return MAPPER.map(userDao.findByUsername(username), UserInfoDto.class);
    }
}
