package com.forum.forum.service;

import com.forum.forum.dao.QuestionDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.TopicDto;
import com.forum.forum.model.Question;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService extends AbstarctPostService{

    private QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao, UserDao userDao) {
        super(questionDao, userDao);
        this.questionDao = questionDao;
    }

    public List<TopicDto> getLastTopics(){
        return questionDao.findTop10ByOrderByIdDesc().stream()
                .map(question -> new TopicDto(posted(question.getCreatedAt()), question.getUser().getUsername(), question.getTitle(), question.getDescription()))
                .collect(Collectors.toList());
    }
}
