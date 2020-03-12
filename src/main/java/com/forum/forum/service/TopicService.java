package com.forum.forum.service;

import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.*;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.other.DateFormater;
import com.forum.forum.other.specification.ProfileSpecification;
import com.forum.forum.other.specification.TypeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TopicService{

    private TopicDao topicDao;
    private UserDao userDao;
    private CommentDao commentDao;
    private SimpleDateFormat simpleDateFormat;

    @Autowired
    public TopicService(TopicDao topicDao, UserDao userDao, CommentDao commentDao) {
        this.topicDao = topicDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
        simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
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

    public void deleteTopic(Long id){
        topicDao.deleteById(id);
    }

    public List<TopicOutputDto> getPageOf15Topics(TypeSpecification typeSpecification, Pageable pageable){
        Pageable pageableValue = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
        return topicDao.findAll(typeSpecification, pageableValue).stream()
                .map(topic -> new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), DateFormater.posted(topic.getCreatedAt()), topic.getId(), topic.getComments().size()))
                .collect(Collectors.toList());
    }

    public List<TopicOutputDto> getPageOf15Topics(Pageable pageable){
        Pageable pageableValue = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
        return topicDao.findAll(pageableValue).stream()
                .map(topic -> new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), DateFormater.posted(topic.getCreatedAt()), topic.getId(), topic.getComments().size()))
                .collect(Collectors.toList());
    }

    public List<TopicProfileDto> getPageOfTopic(ProfileSpecification profileSpecification, Pageable pageable){
        Pageable pageableValue = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
        return topicDao.findAll(profileSpecification, pageableValue).stream()
                .map(topic -> new TopicProfileDto(topic.getUser().getUsername(), simpleDateFormat.format(topic.getCreatedAt()),topic.getTitle(), topic.getComments().size(),topic.getId()))
                .collect(Collectors.toList());
    }

    public TopicOutputDto getTopic(Long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic foundTopic = topicOptional.orElseThrow(()-> new RuntimeException("Topic doesn't exist"));
        return new TopicOutputDto(foundTopic.getUser().getUsername(), foundTopic.getTitle(), foundTopic.getDescription(), foundTopic.getType().getDisplayName(), DateFormater.posted(foundTopic.getCreatedAt()), foundTopic.getId(), foundTopic.getComments().size());
    }

    public long getPageNumber(TypeSpecification typeSpecification, Pageable pageable){
        if(typeSpecification.getType() == null){
            return getPageNumber(pageable);
        }
        return (long) Math.ceil((double) topicDao.count(typeSpecification)/pageable.getPageSize());
    }

    public long getPageNumber(Pageable pageable){
        return (long) Math.ceil((double) topicDao.count()/pageable.getPageSize());
    }

    public long getProfilePageNumber(ProfileSpecification profileSpecification, Pageable pageable){
        return (long) Math.ceil((double) topicDao.count(profileSpecification)/pageable.getPageSize());
    }

}
