package com.forum.forum.service;

import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.*;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.other.builder.TopicBuilder;
import com.forum.forum.other.exceptions.TopicNotFoundException;
import com.forum.forum.other.exceptions.UserNotFoundException;
import com.forum.forum.other.specification.ProfileSpecification;
import com.forum.forum.other.specification.TypeSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TopicService{

    private final ModelMapper mapper = new ModelMapper();

    private TopicDao topicDao;
    private UserDao userDao;

    @Autowired
    public TopicService(TopicDao topicDao, UserDao userDao) {
        this.topicDao = topicDao;
        this.userDao = userDao;
    }

    public void addTopic(TopicInputDto topicInputDto, String username){
        Optional<User> userOptional =  userDao.findByUsername(username);
        User user = userOptional.orElseThrow(UserNotFoundException::new);
        Topic topic = TopicBuilder.builder()
                .title(topicInputDto.getTitle())
                .description(topicInputDto.getDescription())
                .type(topicInputDto.getType())
                .user(user)
                .build();
        topicDao.save(topic);
    }

    public void deleteTopic(Long id){
        topicDao.deleteById(id);
    }

    public List<TopicOutputDto> getPageByTypeSpecification(TypeSpecification typeSpecification, Pageable pageable){
        Pageable pageableValue = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
        return topicDao.findAll(typeSpecification, pageableValue).stream()
                .map(topic -> mapper.map(topic, TopicOutputDto.class))
                .collect(Collectors.toList());
    }

    public List<TopicOutputDto> getPage(Pageable pageable){
        Pageable pageableValue = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
        return topicDao.findAll(pageableValue).stream()
                .map(topic -> mapper.map(topic, TopicOutputDto.class))
                .collect(Collectors.toList());
    }

    public List<TopicProfileDto> getPageByProfileSpecification(ProfileSpecification profileSpecification, Pageable pageable){
        Pageable pageableValue = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
        return topicDao.findAll(profileSpecification, pageableValue).stream()
                .map(topic -> mapper.map(topic, TopicProfileDto.class))
                .collect(Collectors.toList());
    }

    public TopicOutputDto getTopic(Long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic foundTopic = topicOptional.orElseThrow(TopicNotFoundException::new);
        return mapper.map(foundTopic, TopicOutputDto.class);
    }

    public List<TopicOutputDto> getPopularTopic(){
        List<Topic> topics = topicDao.findTop3ByCreatedAtGreaterThanOrderByVotesDesc(LocalDateTime.now().minusDays(1));
        return topics.stream()
                .map(topic -> mapper.map(topic, TopicOutputDto.class))
                .collect(Collectors.toList());
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
