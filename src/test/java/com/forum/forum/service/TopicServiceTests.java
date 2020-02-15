package com.forum.forum.service;

import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceTests {

    @Mock
    private UserDao userDao;

    @Mock
    private TopicDao topicDao;

    @InjectMocks
    private TopicService topicService;

    @Test
    public void shouldAddTopic(){
        //given
        User user = new User("user", "email", "password", "USER");
        Topic topic = new Topic("title", "description");
        given(userDao.findByUsername(Mockito.any())).willReturn(user);
        given(userDao.save(Mockito.any(User.class))).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        given(topicDao.save(Mockito.any(Topic.class))).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        //when
        topicService.addPost(topic, "user");

        //then
        verify(userDao).save(user);
        verify(topicDao).save(topic);

        assertEquals(user.getUsername(), topic.getUser().getUsername());
        assertEquals(1, user.getTopics().size());
    }
}