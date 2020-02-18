package com.forum.forum.dao;

import com.forum.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicDao extends JpaRepository<Topic, Long>{
    List<Topic> findTop15ByOrderByIdDesc();
}
