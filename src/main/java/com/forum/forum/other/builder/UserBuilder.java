package com.forum.forum.other.builder;

import com.forum.forum.model.*;
import com.forum.forum.other.enums.Role;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class UserBuilder {

    private User user = new User();

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public UserBuilder id(Long id){
        user.setId(id);
        return this;
    }

    public UserBuilder username(String username){
        user.setUsername(username);
        return this;
    }

    public UserBuilder roles(Set<Role> roles){
        user.setRoles(roles);
        return this;
    }

    public UserBuilder email(String email){
        user.setEmail(email);
        return this;
    }

    public UserBuilder password(String password){
        user.setPassword(password);
        return this;
    }

    public UserBuilder token(Token token){
        user.setToken(token);
        return this;
    }

    public UserBuilder topics(Set<Topic> topics){
        user.setTopics(topics);
        return this;
    }

    public UserBuilder comments(Set<Comment> comments){
        user.setComments(comments);
        return this;
    }

    public UserBuilder createdAt(LocalDateTime date){
        user.setCreatedAt(date);
        return this;
    }

    public UserBuilder reports(Set<Report> reports){
        user.setReport(reports);
        return this;
    }

    public UserBuilder motto(String motto){
        user.setMotto(motto);
        return this;
    }

    public User build(){
        return user;
    }
}
