package com.forum.forum.service;

import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.CommentInputDto;
import com.forum.forum.dto.TopicInputDto;
import com.forum.forum.dto.TopicOutputDto;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicService{

    private TopicDao topicDao;
    private UserDao userDao;
    private CommentDao commentDao;

    @Autowired
    public TopicService(TopicDao topicDao, UserDao userDao, CommentDao commentDao) {
        this.topicDao = topicDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
    }

    public void addTopic(TopicInputDto topicInputDto, String username){
        User user =  userDao.findByUsername(username);
        Topic topic = new Topic(topicInputDto.getTitle(), topicInputDto.getDescription());
        topic.setUser(user);
        topic.setType(topicInputDto.getType());
        user.getTopics().add(topic);
        userDao.save(user);
        topicDao.save(topic);

    }

    public List<TopicOutputDto> getLast15Topics(){
        return topicDao.findTop15ByOrderByIdDesc().stream()
                .map(topic -> new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), posted(topic.getCreatedAt())))
                .collect(Collectors.toList());
    }

    public TopicOutputDto getTopic(Long id){
        Optional<Topic> topic = topicDao.findById(id);
        //TODO
        return new TopicOutputDto(topic.get().getUser().getUsername(), topic.get().getTitle(), topic.get().getDescription(), topic.get().getType().getDisplayName(), posted(topic.get().getCreatedAt()));
    }

    public void addComment(CommentInputDto commentInputDto, String username, Long id){
        User user = userDao.findByUsername(username);
        Optional<Topic> topic = topicDao.findById(id);
        Comment comment = new Comment(commentInputDto.getComment());
        comment.setTopic(topic.get());
        comment.setUser(user);
        user.getComments().add(comment);
        topic.get().getComments().add(comment);
        commentDao.save(comment);
        userDao.save(user);
        topicDao.save(topic.get());
    }

    private String posted(Date postedDate){
        long actualDate = new Date().getTime();
        long remain = (actualDate - postedDate.getTime())/1000;
        if(remain < 60){
            return remain + " secs";
        }
        remain /= 60;
        if(remain < 60){
            return remain + " mins";
        }
        remain /= 60;
        if(remain < 60){
            return remain + " hours";
        }
        remain /= 24;
        if(remain < 365){
            return remain + " days";
        }
        remain /= 365;
        return remain + " years";
    }
}
