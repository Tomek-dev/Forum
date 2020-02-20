package com.forum.forum.service;

import com.forum.forum.Type;
import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.CommentInputDto;
import com.forum.forum.dto.TopicInputDto;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceTests {

    @Mock
    private UserDao userDao;

    @Mock
    private TopicDao topicDao;

    @Mock
    private CommentDao commentDao;

    @InjectMocks
    private TopicService topicService;

    private User user;

    @Before
    public void init(){
        user = new User("user", "email", "password", "USER");
        given(userDao.findByUsername(Mockito.any())).willReturn(user);
        given(userDao.save(Mockito.any(User.class))).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
    }

    @Test
    public void shouldAddTopic(){
        //given
        final Topic[] savedTopic = new Topic[1];
        TopicInputDto topicInputDto = new TopicInputDto(Type.JAVA ,"title", "description");
        given(topicDao.save(Mockito.any(Topic.class))).willAnswer(invocationOnMock -> {
            savedTopic[0] = (Topic) invocationOnMock.getArguments()[0];
            return invocationOnMock.getArguments()[0];
        });

        //when
        topicService.addTopic(topicInputDto, "user");

        //then
        verify(userDao).save(user);
        verify(topicDao).save(any());

        assertEquals(user.getUsername(), savedTopic[0].getUser().getUsername());
        assertEquals(1, user.getTopics().size());
        assertEquals(Type.JAVA, savedTopic[0].getType());
    }

    @Test
    public void shouldAddComment(){
        //given
        Topic topic = new Topic();
        final Comment[] savedComment = new Comment[1];
        given(topicDao.findById(Mockito.any())).willReturn(java.util.Optional.of(topic));
        given(commentDao.save(Mockito.any(Comment.class))).willAnswer(invocationOnMock -> {
            savedComment[0] = (Comment) invocationOnMock.getArguments()[0];
            return invocationOnMock.getArguments()[0];
        });

        //when
        topicService.addComment(new CommentInputDto("comment"), "user", 1l);

        //then
        verify(topicDao).save(topic);
        verify(userDao).save(user);
        verify(commentDao).save(any());

        assertTrue(user.getComments().contains(savedComment[0]));
        assertTrue(topic.getComments().contains(savedComment[0]));
        assertEquals(user.getUsername(), savedComment[0].getUser().getUsername());
        assertEquals(topic, savedComment[0].getTopic());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowException(){
        //given
        given(topicDao.findById(Mockito.any())).willReturn(null);

        //when
        topicService.addComment(new CommentInputDto(), "username", 4l);
    }
}