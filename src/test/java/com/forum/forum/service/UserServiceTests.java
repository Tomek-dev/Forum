package com.forum.forum.service;


import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.EmailDto;
import com.forum.forum.dto.RegistrationDto;
import com.forum.forum.model.User;
import com.forum.forum.service.UserService;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    UserDao userDao;

    @Mock
    PasswordEncoder passwordEncoder;

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

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowUsernameNotFoundException(){
        //given
        given(userDao.findByUsernameIgnoreCase(Mockito.any())).willReturn(null);

        //when
        userService.getUserByUserName("user");
    }

    @Test
    public void shouldReturnUserOutputDto(){
        //given
        User user = new User();
        user.setUsername("user");
        given(userDao.findByUsernameIgnoreCase(Mockito.any())).willReturn(user);

        //when
        userService.getUserByUserName("user");

        //then
        verify(userDao).findByUsernameIgnoreCase(any());
        assertEquals("user", userService.getUserByUserName("user").getUsername());
        assertEquals(new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(user.getCreatedAt().getTime()), userService.getUserByUserName("user").getCreatedAt());
    }
}
