package com.forum.forum.service;

import com.forum.forum.dao.TokenDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.EmailDto;
import com.forum.forum.dto.ResetDto;
import com.forum.forum.model.Token;
import com.forum.forum.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ResetServiceTests {

    @Mock
    private TokenDao tokenDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private ResetService resetService;

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowUsernameNotFoundException(){
        //given
        given(tokenDao.findByToken(Mockito.any())).willReturn(new Token(UUID.randomUUID(), new User("user", "email", "password", "USER")));
        given(userDao.findByEmail(Mockito.any())).willReturn(null);
        //when
        resetService.resetPassword(UUID.randomUUID(), new ResetDto());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeException(){
        //given
        given(tokenDao.findByToken(Mockito.any())).willReturn(null);
        //when
        resetService.resetPassword(UUID.randomUUID(), new ResetDto());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowUsernameNotFoundExceptionSendEmail(){
        //given
        given(userDao.findByEmail(Mockito.any())).willReturn(null);
        //when
        resetService.sendToken(new EmailDto("email"));
    }

    @Test
    public void shouldResetPassword(){
        //given
        Token token = new Token();
        User user = new User();
        token.setUser(user);
        user.setToken(token);
        user.setPassword(passwordEncoder.encode("password"));
        ResetDto resetDto = new ResetDto("resetPassword", "resetPassword");
        given(tokenDao.findByToken(Mockito.any())).willReturn(token);
        given(userDao.findByEmail(Mockito.any())).willReturn(user);

        //when
        resetService.resetPassword(UUID.randomUUID(), resetDto);

        //then
        verify(tokenDao).delete(token);

        assertNotEquals("password", user.getPassword());
        assertNull(user.getToken());
    }

    @Test
    public void shouldSendToken(){
        //given
        User user = new User();
        given(userDao.findByEmail(Mockito.any())).willReturn(user);

        //when
        resetService.sendToken(new EmailDto());

        //then
        verify(tokenDao).save(any());

        assertNotNull(user.getToken());
    }
}