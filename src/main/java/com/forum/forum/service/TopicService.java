package com.forum.forum.service;

import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.TopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService extends AbstarctPostService{

    private TopicDao topicDao;

    @Autowired
    public TopicService(TopicDao topicDao, UserDao userDao) {
        super(topicDao, userDao);
        this.topicDao = topicDao;
    }

    public List<TopicDto> getLast10Topics(){
        return topicDao.findTop10ByOrderByIdDesc().stream()
                .map(topic -> new TopicDto(posted(topic.getCreatedAt()), topic.getUser().getUsername(), topic.getTitle(), topic.getDescription()))
                .collect(Collectors.toList());
    }
}
