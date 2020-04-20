package com.forum.forum.service;

import com.forum.forum.dao.UserDao;
import com.forum.forum.model.User;
import com.forum.forum.other.builder.UserBuilder;
import com.forum.forum.other.enums.Role;
import com.forum.forum.service.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTests {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowUsernameNotFoundException(){
        //given
        given(userDao.findByUsername(Mockito.any())).willReturn(Optional.empty());

        //when
        userDetailsService.loadUserByUsername("");
    }

    @Test
    public void shouldReturnUserDetails(){
        //given
        User user = UserBuilder.builder()
                .username("username")
                .roles(Collections.singleton(Role.USER))
                .build();
        given(userDao.findByUsername(Mockito.any())).willReturn(Optional.of(user));

        //then
        assertEquals("username", userDetailsService.loadUserByUsername("").getUsername());
        assertEquals(Collections.singleton(Role.USER), userDetailsService.loadUserByUsername("").getAuthorities());
    }
}