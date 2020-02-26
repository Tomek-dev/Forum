package com.forum.forum.service;

import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TokenDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.EmailDto;
import com.forum.forum.dto.RegistrationDto;
import com.forum.forum.dto.UserOutputDto;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Token;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService {

    private UserDao userDao;
    private TopicDao topicDao;
    private CommentDao commentDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, TopicDao topicDao, CommentDao commentDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.topicDao = topicDao;
        this.commentDao = commentDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(RegistrationDto registrationDto){
        userDao.save(new User(registrationDto.getUsername(), registrationDto.getEmail(),passwordEncoder.encode(registrationDto.getPassword()), "USER"));
    }

    public UserOutputDto getUserByUsername(String username){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not authorized."));
        return new UserOutputDto(user.getUsername(), new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(user.getCreatedAt().getTime()), user.getTopics().size(), user.getComments().size());
    }

    public void deleteUser(String username){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Set<Topic> topicSet = user.getTopics();
        Set<Comment> commentSet = user.getComments();
        commentDao.deleteAll(commentSet);
        topicDao.deleteAll(topicSet);
        userDao.delete(user);
    }
}
