package com.forum.forum.service;

import com.forum.forum.dao.QuestionDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.model.Question;
import com.forum.forum.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTests {

    @Mock
    private UserDao userDao;

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionService questionService;

    @Test
    public void shouldAddNewQuestion(){
        //given
        User user = new User("user", "email", "password", "USER");
        Question question = new Question("title", "description");
        given(userDao.findByUsername(Mockito.any())).willReturn(user);
        given(userDao.save(Mockito.any(User.class))).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        given(questionDao.save(Mockito.any(Question.class))).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        //when
        questionService.addQuestion(question, "user");

        //then
        verify(userDao).save(user);
        verify(questionDao).save(question);

        assertEquals(user.getUsername(), question.getUser().getUsername());
        assertEquals(1, user.getQuestions().size());
    }
}