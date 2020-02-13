package com.forum.forum.service;

import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    private TopicDao topicDao;
    private UserDao userDao;

    @Autowired
    public TopicService(TopicDao topicDao, UserDao userDao) {
        this.topicDao = topicDao;
        this.userDao = userDao;
    }

    public void addTopic(Topic topic, String username){
        User user = userDao.findByUsername(username);
        topic.setUser(user);
        user.getTopics().add(topic);
        topicDao.save(topic);
        userDao.save(user);
    }
}
