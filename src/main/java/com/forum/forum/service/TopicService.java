package com.forum.forum.service;

import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.TopicInputDto;
import com.forum.forum.dto.TopicOutputDto;
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

    @Autowired
    public TopicService(TopicDao topicDao, UserDao userDao) {
        this.topicDao = topicDao;
        this.userDao = userDao;
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
        /*List<Topic> optionalTopicsOutputDto = topicDao.findTop15ByOrderByIdDesc();
        List<TopicOutputDto> topicOutputDto = new ArrayList<>();
        for (Topic topic : optionalTopicsOutputDto) {
            topicOutputDto.add(new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), posted(topic.getCreatedAt())));
        }
        return topicOutputDto;*/
        return topicDao.findTop15ByOrderByIdDesc().stream()
                .map(topic -> new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), posted(topic.getCreatedAt())))
                .collect(Collectors.toList());
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
