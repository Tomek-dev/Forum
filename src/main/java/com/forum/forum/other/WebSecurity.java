package com.forum.forum.other;

import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class WebSecurity {


    private TopicDao topicDao;
    private CommentDao commentDao;

    @Autowired
    public WebSecurity(TopicDao topicDao, CommentDao commentDao) {
        this.topicDao = topicDao;
        this.commentDao = commentDao;
    }

    public boolean checkUser(Authentication authentication, String username){
        String authName = authentication.getName();
        return authName.equalsIgnoreCase(username) || authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
    }

    public boolean checkTopic(Authentication authentication, Long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        if(topicOptional.isPresent()){
            Topic topic = topicOptional.get();
            return topic.getUser().getUsername().equals(authentication.getName()) || authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
        }
        return false;
    }

    public boolean checkComment(Authentication authentication, Long id){
        Optional<Comment> commentOptional = commentDao.findById(id);
        if(commentOptional.isPresent()){
            Comment comment = commentOptional.get();
            return comment.getUser().getUsername().equals(authentication.getName()) || authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
        }
        return false;
    }
}
