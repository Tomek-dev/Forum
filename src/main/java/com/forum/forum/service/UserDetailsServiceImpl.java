package com.forum.forum.service;

import com.forum.forum.dao.UserDao;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("User not authorized.");
        }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
        return new User(user.getUsername(), user.getEmail(), user.getPassword(), grantedAuthority.toString());
    }
}
