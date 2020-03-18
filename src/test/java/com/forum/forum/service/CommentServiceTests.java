package com.forum.forum.service;

import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.CommentInputDto;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.other.DateFormater;
import com.forum.forum.other.builder.UserBuilder;
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
public class CommentServiceTests {

    @Mock
    private UserDao userDao;

    @Mock
    private TopicDao topicDao;

    @Mock
    private CommentDao commentDao;

    @InjectMocks
    private CommentService commentService;

    private User user;

    @Before
    public void init(){
        user = UserBuilder.builder()
                .username("user")
                .build();
        given(userDao.findByUsername(Mockito.any())).willReturn(user);
        given(userDao.save(Mockito.any(User.class))).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowException(){
        //given
        given(topicDao.findById(Mockito.any())).willReturn(null);

        //when
        commentService.addComment(new CommentInputDto(), "username", 4l);
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
        commentService.addComment(new CommentInputDto("comment"), "user", 1l);

        //then
        verify(topicDao).save(topic);
        verify(userDao).save(user);
        verify(commentDao).save(any());

        assertTrue(user.getComments().contains(savedComment[0]));
        assertTrue(topic.getComments().contains(savedComment[0]));
        assertEquals(user.getUsername(), savedComment[0].getUser().getUsername());
        assertEquals(topic, savedComment[0].getTopic());
    }

    @Test
    public void shouldDeleteComment(){
        //when
        commentService.deleteComment(4L);

        //then
        verify(commentDao).deleteById(4L);
    }

    @Test
    public void shouldGetCommentsByTopicId(){
        //given
        Topic topic = new Topic();
        Comment comment = new Comment();
        comment.setComment("comment");
        comment.setUser(user);
        topic.getComments().add(comment);
        given(topicDao.findById(Mockito.any())).willReturn(java.util.Optional.of(topic));

        //then
        assertEquals("comment", commentService.getComments(1L).get(0).getComment());
        assertEquals("user", commentService.getComments(1L).get(0).getUsername());
        assertEquals(DateFormater.posted(comment.getCreatedAt()), commentService.getComments(1L).get(0).getPostedAt());
    }
}