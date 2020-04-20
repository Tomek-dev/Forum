package com.forum.forum.service;

import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.MottoDto;
import com.forum.forum.dto.RegistrationDto;
import com.forum.forum.dto.UserInfoDto;
import com.forum.forum.dto.UserOutputDto;
import com.forum.forum.model.User;
import com.forum.forum.other.builder.UserBuilder;
import com.forum.forum.other.enums.Role;
import com.forum.forum.other.exceptions.UserNotFoundException;
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

    private final ModelMapper mapper = new ModelMapper();


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
                .roles(Collections.singleton(Role.USER))
                .createdAt(LocalDateTime.now())
                .build();
        userDao.save(user);
    }

    public UserOutputDto getUserByUsername(String username){
        Optional<User> userOptional = userDao.findByUsernameIgnoreCase(username);
        User user = userOptional.orElseThrow(UserNotFoundException::new);
        return mapper.map(user, UserOutputDto.class);
    }

    @Transactional
    public void deleteUser(String username){
        userDao.deleteByUsernameIgnoreCase(username);
    }

    public void setMotto(String username, MottoDto mottoDto){
        Optional<User> userOptional = userDao.findByUsernameIgnoreCase(username);
        User user = userOptional.orElseThrow(UserNotFoundException::new);
        user.setMotto(mottoDto.getMotto());
        userDao.save(user);
    }

    public UserInfoDto getUser(String username){
        Optional<User> userOptional = userDao.findByUsername(username);
        User user = userOptional.orElseThrow(UserNotFoundException::new);
        return mapper.map(user, UserInfoDto.class);
    }
}
