package com.forum.forum.service;

import com.forum.forum.dao.UserDao;
import com.forum.forum.model.User;
import com.forum.forum.other.builder.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsername(s));
        User foundUser = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not authorized."));
        Set<String> grantedAuthority = foundUser.getRole().stream()
                .map(SimpleGrantedAuthority::new)
                .map(SimpleGrantedAuthority::toString)
                .collect(Collectors.toSet());
        return UserBuilder.builder()
                .username(foundUser.getUsername())
                .password(foundUser.getPassword())
                .email(foundUser.getEmail())
                .roles(grantedAuthority)
                .build();
    }
}
