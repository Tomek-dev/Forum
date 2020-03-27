package com.forum.forum.service;

import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.VoteDao;
import com.forum.forum.dto.TopicProfileDto;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.model.Vote;
import com.forum.forum.other.exceptions.TopicNotFoundException;
import com.forum.forum.other.specification.ProfileSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    private TopicDao topicDao;
    private VoteDao voteDao;

    @Autowired
    public VoteService(TopicDao topicDao, VoteDao voteDao) {
        this.topicDao = topicDao;
        this.voteDao = voteDao;
    }

    public void vote(User user, Long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic topic = topicOptional.orElseThrow(TopicNotFoundException::new);
        Optional<Vote> voteOptional = voteDao.findByUserAndTopic(user, topic);
        if(voteOptional.isPresent()){
            voteDao.delete(voteOptional.get());
            return;
        }
        Vote vote = new Vote.Builder()
                .topic(topic)
                .user(user)
                .build();
        voteDao.save(vote);
    }
}
