package com.forum.forum.service;

import com.forum.forum.dao.UserDao;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(foundUser.getRole());
        return new User(foundUser.getUsername(), foundUser.getEmail(), foundUser.getPassword(), grantedAuthority.toString());
    }
}
