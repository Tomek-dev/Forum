package com.forum.forum.service;

import com.forum.forum.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserDetailsServiceImplTests {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Test
    void shouldThrowUsernameNotFoundException(){
        try{
            userDetailsService.loadUserByUsername("");
            fail("Exception wasn't thrown!");
        }catch (UsernameNotFoundException exception){
            assertEquals("User not authorized.", exception.getMessage());
        }
    }
}