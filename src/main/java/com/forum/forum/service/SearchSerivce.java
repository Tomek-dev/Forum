package com.forum.forum.service;

import com.forum.forum.dao.TopicDao;
import com.forum.forum.dto.TopicOutputDto;
import com.forum.forum.other.DateFormater;
import com.forum.forum.other.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchSerivce {

    private TopicDao topicDao;

    @Autowired
    public SearchSerivce(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public List<TopicOutputDto> getPageBySearch(SearchSpecification searchSpecification, Pageable pageable){
        return topicDao.findAll(searchSpecification, pageable).stream()
                .map(topic -> new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), DateFormater.posted(topic.getCreatedAt()), topic.getId()))
                .collect(Collectors.toList());
    }

    public long getPageNumber(SearchSpecification searchSpecification, Pageable pageable){
        return (long) Math.ceil((double) topicDao.count(searchSpecification)/pageable.getPageSize());
    }
}
