package com.forum.forum.service;

import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService extends AbstarctPostService{

    @Autowired
    public TopicService(TopicDao topicDao, UserDao userDao) {
        super(topicDao, userDao);
    }
}
