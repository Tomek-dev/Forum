package com.forum.forum.service;

import com.forum.forum.dao.QuestionDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.model.Question;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends AbstarctPostService{

    @Autowired
    public QuestionService(QuestionDao questionDao, UserDao userDao) {
        super(questionDao, userDao);
    }
}
