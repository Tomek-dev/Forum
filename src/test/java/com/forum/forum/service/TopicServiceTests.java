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

    @Test
    public void shouldThrowRunTimeException(){
        assertThrows(RuntimeException.class, () -> topicService.getLast15TopicsByType("test"));
    }

    @Test
    public void shouldReturnTop15TopicsByType(){
        //when
        topicService.getLast15TopicsByType("java");

        //then
        verify(topicDao).findTop15ByTypeOrderByIdDesc(Type.JAVA);
    }

    @Test
    public void shouldReturnListOfTopicOutputDto(){
        //when
        topicService.getLast15Topics();

        //then
        verify(topicDao).findTop15ByOrderByIdDesc();
    }

    @Test
    public void shouldThrowIndexOutOfBoundsException(){
        //given
        given(topicDao.count()).willReturn(30L);

        //then
        assertThrows(IndexOutOfBoundsException.class, () -> topicService.get15TopicByPage(3));
        assertThrows(IndexOutOfBoundsException.class, () -> topicService.get15TopicByPage(-1));
    }

    @Test
    public void shouldReturnListOfTopicOutputDtoByPage(){
        //given
        given(topicDao.count()).willReturn(30L);

        //when
        topicService.get15TopicByPage(1);

        //then
        verify(topicDao).findByIdBetweenOrderByIdDesc(16, 30);
    }

    @Test
    public void shouldInitGetLast15Topics(){
        //given
        given(topicDao.count()).willReturn(12L);

        //when
        topicService.get15TopicByPage(1);

        //then
        verify(topicDao).findTop15ByOrderByIdDesc();
    }

    @Test
    public void shouldThrowUsernameNotFoundException(){
        //given
        given(userDao.findByUsername(Mockito.any())).willReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> topicService.getLast15TopicsByUsername("user"));
    }

    @Test
    public void shouldReturnTopicProfileDtoByUsername(){
        //given
        User user = new User();
        user.setUsername("user");
        Topic topic = new Topic();
        topic.setUser(user);
        topic.setTitle("title");
        user.getTopics().add(topic);
        given(userDao.findByUsername(Mockito.any())).willReturn(user);

        //when
        topicService.getLast15TopicsByUsername("user");

        //then
        assertEquals("title", topicService.getLast15TopicsByUsername("user").get(0).getTitle());
        assertEquals(0, topicService.getLast15TopicsByUsername("user").get(0).getCommentsCount());
    }

    @Test
    public void shouldReturnTopicProfileDtoOfCommentedTopicByUsername(){
        //given
        Comment comment = new Comment();
        Topic topic = new Topic();
        User user = new User();
        user.setUsername("user");
        user.getTopics().add(topic);
        user.getComments().add(comment);
        comment.setUser(user);
        comment.setTopic(topic);
        topic.setUser(user);
        topic.setTitle("title");
        topic.getComments().add(comment);

        given(userDao.findByUsername(Mockito.any())).willReturn(user);

        //then
        assertEquals("title", topicService.getLast15CommentedTopicsByUsername("user").get(0).getTitle());
        assertEquals("user", topicService.getLast15CommentedTopicsByUsername("user").get(0).getUsername());
        assertEquals(1, topicService.getLast15CommentedTopicsByUsername("user").get(0).getCommentsCount());
    }

    @Test
    public void shouldGetCommentsByTopic(){
        //given
        Topic topic = new Topic();
        Comment comment = new Comment();
        User user = new User();
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
        given(topicDao.findById(Mockito.any())).willReturn(null);
    }
}