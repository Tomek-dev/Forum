package com.forum.forum.service;

import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.*;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.other.builder.TopicBuilder;
import com.forum.forum.other.exceptions.TopicNotFoundException;
import com.forum.forum.other.specification.ProfileSpecification;
import com.forum.forum.other.specification.TypeSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TopicService{

    private static final ModelMapper MAPPER = new ModelMapper();

    private TopicDao topicDao;
    private UserDao userDao;

    @Autowired
    public TopicService(TopicDao topicDao, UserDao userDao) {
        this.topicDao = topicDao;
        this.userDao = userDao;
    }

    public void addTopic(TopicInputDto topicInputDto, String username){
        User user =  userDao.findByUsername(username);
        Topic topic = TopicBuilder.builder()
                .title(topicInputDto.getTitle())
                .description(topicInputDto.getDescription())
                .type(topicInputDto.getType())
                .user(user)
                .build();
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
                .map(topic -> MAPPER.map(topic, TopicOutputDto.class))
                .collect(Collectors.toList());
    }

    public List<TopicOutputDto> getPageOf15Topics(Pageable pageable){
        Pageable pageableValue = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
        return topicDao.findAll(pageableValue).stream()
                .map(topic -> MAPPER.map(topic, TopicOutputDto.class))
                .collect(Collectors.toList());
    }

    public List<TopicProfileDto> getPageOfTopic(ProfileSpecification profileSpecification, Pageable pageable){
        Pageable pageableValue = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
        return topicDao.findAll(profileSpecification, pageableValue).stream()
                .map(topic -> MAPPER.map(topic, TopicProfileDto.class))
                .collect(Collectors.toList());
    }

    public TopicOutputDto getTopic(Long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic foundTopic = topicOptional.orElseThrow(TopicNotFoundException::new);
        return MAPPER.map(foundTopic, TopicOutputDto.class);
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
