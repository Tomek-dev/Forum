package com.forum.forum.service;

import com.forum.forum.dao.StarDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.model.Star;
import com.forum.forum.model.User;
import com.forum.forum.other.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StarService {

    private StarDao starDao;
    private UserDao userDao;

    @Autowired
    public StarService(StarDao starDao, UserDao userDao) {
        this.starDao = starDao;
        this.userDao = userDao;
    }

    public void star(String username, User user){
        Optional<User> userOptional = userDao.findByUsernameIgnoreCase(username);
        User foundUser = userOptional.orElseThrow(UserNotFoundException::new);
        Optional<Star> starOptional = starDao.findByStarUserAndUser(foundUser, user);
        if(starOptional.isPresent()){
            starDao.delete(starOptional.get());
            return;
        }
        Star star = new Star.Builder()
                .starUser(foundUser)
                .user(user)
                .build();
        starDao.save(star);
    }
}
