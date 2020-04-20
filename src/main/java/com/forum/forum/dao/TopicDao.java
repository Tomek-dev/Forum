package com.forum.forum.dao;

import com.forum.forum.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TopicDao extends JpaRepository<Topic, Long>, JpaSpecificationExecutor<Topic> {
    Page<Topic> findAll(Pageable pageable);
    List<Topic> findTop3ByCreatedAtGreaterThanOrderByVotesDesc(LocalDateTime minusDays);
}
