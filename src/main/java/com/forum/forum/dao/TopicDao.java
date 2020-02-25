package com.forum.forum.dao;

import com.forum.forum.enums.Type;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicDao extends JpaRepository<Topic, Long>{
    List<Topic> findTop15ByOrderByIdDesc();
    List<Topic> findByIdBetweenOrderByIdDesc(long start, long end); //TODO to change
    List<Topic> findTop15ByTypeOrderByIdDesc(Type type);
    List<Topic> findByTypeAndIdBetweenOrderByIdDesc(Type type, long start, long end); //TODO to change
    List<Topic> findByUserAndIdBetweenOrderByIdDesc(User user, long start, long end); //TODO to change
    long countByType(Type type);
}
