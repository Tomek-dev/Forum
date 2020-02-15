package com.forum.forum.service;

import com.forum.forum.dao.UserDao;
import com.forum.forum.model.Post;
import com.forum.forum.model.Question;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstarctPostService {

    protected JpaRepository repository;
    protected UserDao userDao;

    public AbstarctPostService(JpaRepository repository, UserDao userDao) {
        this.repository = repository;
        this.userDao = userDao;
    }

    public void addPost(Post post, String username){
        User user = userDao.findByUsername(username);
        post.setUser(user);
        if(post instanceof Question){
            user.getQuestions().add((Question) post);
        }else if(post instanceof Topic){
            user.getTopics().add((Topic) post);
        }
        repository.save(post);
        userDao.save(user);
    }
}
