package com.forum.forum.service;

import com.forum.forum.dao.TokenDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.EmailDto;
import com.forum.forum.dto.ResetDto;
import com.forum.forum.model.Token;
import com.forum.forum.model.User;
import com.forum.forum.other.exceptions.TokenNotFoundException;
import com.forum.forum.other.exceptions.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
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

    @Test(expected = TokenNotFoundException.class)
    public void shouldThrowRuntimeException(){
        //given
        given(tokenDao.findByToken(Mockito.any())).willReturn(Optional.empty());
        //when
        resetService.resetPassword(UUID.randomUUID(), new ResetDto());
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUsernameNotFoundExceptionSendEmail(){
        //given
        given(userDao.findByEmail(Mockito.any())).willReturn(Optional.empty());
        //when
        resetService.createToken(new EmailDto("email"));
    }

    @Test
    public void shouldResetPassword(){
        //given
        User user = new User();
        Token token = new Token.Builder()
                .user(user)
                .build();
        user.setToken(token);
        user.setPassword("password");
        ResetDto resetDto = new ResetDto("resetPassword", "resetPassword");
        given(tokenDao.findByToken(Mockito.any())).willReturn(Optional.of(token));

        //when
        resetService.resetPassword(UUID.randomUUID(), resetDto);

        //then
        verify(tokenDao).delete(token);

        assertNotEquals("password", user.getPassword());
        assertNull(user.getToken());
    }

    @Test
    public void shouldCreateToken(){
        //given
        User user = new User();
        given(userDao.findByEmail(Mockito.any())).willReturn(Optional.of(user));

        //when
        resetService.createToken(new EmailDto());

        //then
        verify(tokenDao).save(any());
    }
}