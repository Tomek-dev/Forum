package com.forum.forum.service;


import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.MottoDto;
import com.forum.forum.dto.RegistrationDto;
import com.forum.forum.model.User;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.forum.forum.other.exceptions.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    private UserDao userDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TopicDao topicDao;

    @Mock
    private CommentDao commentDao;

    @InjectMocks
    UserService userService;

    @Test
    public void shouldAddUser(){
        //given
        RegistrationDto registrationDto = new RegistrationDto("user", "email", "password", "password");
        //when
        userService.addUser(registrationDto);
        //then
        verify(userDao).save(any());
    }

    @Test
    public void shouldThrowUsernameNotFoundException(){
        //given
        given(userDao.findByUsernameIgnoreCase(Mockito.any())).willReturn(Optional.empty());

        //then
        assertThrows(UserNotFoundException.class, () -> userService.setMotto("user", new MottoDto()));
    }

    @Test
    public void shouldReturnUserOutputDto(){
        //given
        User user = new User();
        user.setUsername("user");
        given(userDao.findByUsernameIgnoreCase(Mockito.any())).willReturn(java.util.Optional.of(user));

        //when
        userService.getUserByUsername("user");

        //then
        verify(userDao).findByUsernameIgnoreCase(any());
        assertEquals("user", userService.getUserByUsername("user").getUsername());
        assertEquals(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH).format(user.getCreatedAt()), userService.getUserByUsername("user").getCreatedAt());
    }

    @Test
    public void shouldDeleteUser(){
        //when
        userService.deleteUser("user");

        //then
        verify(userDao).deleteByUsernameIgnoreCase("user");
    }
}
