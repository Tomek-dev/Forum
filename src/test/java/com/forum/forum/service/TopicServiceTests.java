package com.forum.forum.service;

import com.forum.forum.enums.Type;
import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.TopicInputDto;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.other.ProfileSpecification;
import com.forum.forum.other.TypeSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public void shouldThrowTopicNotFoundException(){
        //given
        given(topicDao.findById(Mockito.any())).willReturn(null);

        //then
        assertThrows(RuntimeException.class, () -> topicService.getTopic(4L));
        //assertThrows(RuntimeException.class, () -> topicService.deleteTopic(4L));
    }

    @Test
    public void shouldDeleteTopic(){
        //when
        topicService.deleteTopic(4L);

        //then
        verify(topicDao).deleteById(4L);
    }

    @Test
    public void shouldGetTopic(){
        //given
        Topic topic = new Topic();
        User user = new User();
        user.setUsername("user");
        topic.setTitle("title");
        topic.setDescription("description");
        topic.setId(1L);
        topic.setType(Type.JAVA);
        topic.setUser(user);
        given(topicDao.findById(Mockito.any())).willReturn(java.util.Optional.of(topic));

        //when
        topicService.getTopic(4L);

        //then
        verify(topicDao).findById(4L);
        assertEquals("user", topicService.getTopic(4L).getUsername());
        assertEquals("title", topicService.getTopic(4L).getTitle());
        assertEquals("description", topicService.getTopic(4L).getDescription());
        assertEquals(0, topicService.getTopic(4L).getCommentSize());
    }

    @Test
    public void shouldReturnPageNumberByTypeSpecification(){
        //given
        TypeSpecification typeSpecification = new TypeSpecification("java");
        given(topicDao.count(Mockito.any(TypeSpecification.class))).willReturn(34L);

        //then
        assertEquals(3, topicService.getPageNumber(typeSpecification, PageRequest.of(1,15)));
        assertEquals(4, topicService.getPageNumber(typeSpecification, PageRequest.of(1,10)));
        assertEquals(2, topicService.getPageNumber(typeSpecification, PageRequest.of(1,20)));
    }

    @Test
    public void shouldReturnPageNumber(){
        //given
        given(topicDao.count()).willReturn(34L);

        //then
        assertEquals(3, topicService.getPageNumber(PageRequest.of(1,15)));
        assertEquals(4, topicService.getPageNumber(PageRequest.of(1,10)));
        assertEquals(2, topicService.getPageNumber(PageRequest.of(1,20)));
    }

    @Test
    public void shouldReturnPageNumberByProfileSpecification(){
        //given
        ProfileSpecification profileSpecification = new ProfileSpecification("comment");
        given(topicDao.count(Mockito.any(ProfileSpecification.class))).willReturn(34L);

        //then
        assertEquals(3, topicService.getProfilePageNumber(profileSpecification, PageRequest.of(1,15)));
        assertEquals(4, topicService.getProfilePageNumber(profileSpecification, PageRequest.of(1,10)));
        assertEquals(2, topicService.getProfilePageNumber(profileSpecification, PageRequest.of(1,20)));
    }
}