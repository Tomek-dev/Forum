package com.forum.forum.dao;

import com.forum.forum.enums.Type;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TopicDao extends JpaRepository<Topic, Long>, JpaSpecificationExecutor<Topic> {
    Page<Topic> findByType(Pageable pageable, Type type);
    Page<Topic> findAll(Pageable pageable);
    Page<Topic> findByUser(Pageable pageable, User user);
    Page<Topic> findByCommentsIn(Pageable pageable, Collection<Comment> comments);
    long countByType(Type type);
    long countByUser(User user);
}
