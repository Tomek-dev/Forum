package com.forum.forum.service;

import com.forum.forum.dao.QuestionDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.model.Question;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private UserDao userDao;
    private QuestionDao questionDao;

    @Autowired
    public QuestionService(UserDao userDao, QuestionDao questionDao) {
        this.userDao = userDao;
        this.questionDao = questionDao;
    }

    public void addQuestion(Question question, String username){
        User user = userDao.findByUsername(username);
        user.getQuestions().add(question);
        question.setUser(user);
        userDao.save(user);
        questionDao.save(question);
    }
}
