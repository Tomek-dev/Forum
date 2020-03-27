package com.forum.forum.dao;

import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.model.Vote;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteDao extends JpaRepository<Vote, Long>{
    Optional<Vote> findByUserAndTopic(User user, Topic topic);
}
