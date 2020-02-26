package com.forum.forum.service;

import com.forum.forum.enums.Type;
import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
    public void shouldThrowRunTimeException(){
        assertThrows(RuntimeException.class, () -> topicService.getPageOf15TopicsByType("test", 0));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsException(){
        //given
        given(topicDao.count()).willReturn(30L);

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> topicService.getPageOf15Topics(3));
        assertThrows(IndexOutOfBoundsException.class, () -> topicService.getPageOf15Topics(-1));
    }

    @Test
    public void shouldThrowUsernameNotFoundException(){
        //given
        given(userDao.findByUsernameIgnoreCase(Mockito.any())).willReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> topicService.getPageOf15TopicsByUser("user", 0));
        assertThrows(UsernameNotFoundException.class, () -> topicService.getPageOf15TopicsByComment("user", 0));
    }


    @Test
    public void shouldGetCommentsByTopic(){
        //given
        Topic topic = new Topic();
        Comment comment = new Comment();
        user.setUsername("user");
        comment.setComment("comment");
        comment.setUser(user);
        topic.getComments().add(comment);
        given(topicDao.findById(Mockito.any())).willReturn(java.util.Optional.of(topic));

        //then
        assertEquals("comment", topicService.getComments(1L).get(0).getComment());
        assertEquals("user", topicService.getComments(1L).get(0).getUsername());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowTopicNotFoundException(){
        //given
        given(topicDao.findById(Mockito.any())).willReturn(null);

        //when
        topicService.getTopic(4L);
    }
}