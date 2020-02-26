package com.forum.forum.service;

import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.CommentInputDto;
import com.forum.forum.dto.CommentOutputDto;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private TopicDao topicDao;
    private CommentDao commentDao;
    private UserDao userDao;

    @Autowired
    public CommentService(TopicDao topicDao, CommentDao commentDao, UserDao userDao) {
        this.topicDao = topicDao;
        this.commentDao = commentDao;
        this.userDao = userDao;
    }

    public void addComment(CommentInputDto commentInputDto, String username, Long id){
        User user = userDao.findByUsername(username);
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic topic = topicOptional.orElseThrow(() -> new RuntimeException("Topic doesn't exist"));
        Comment comment = new Comment(commentInputDto.getComment());
        comment.setTopic(topic);
        comment.setUser(user);
        user.getComments().add(comment);
        topic.getComments().add(comment);
        commentDao.save(comment);
        userDao.save(user);
        topicDao.save(topic);
    }

    public void deleteComment(Long id){
        Optional<Comment> commentOptional = commentDao.findById(id);
        Comment comment = commentOptional.orElseThrow(() -> new RuntimeException("Comment not found"));
        Topic topic = comment.getTopic();
        topic.getComments().remove(comment);
        User user = comment.getUser();
        user.getComments().remove(comment);
        commentDao.delete(comment);
        userDao.save(user);
        topicDao.save(topic);
    }
}
