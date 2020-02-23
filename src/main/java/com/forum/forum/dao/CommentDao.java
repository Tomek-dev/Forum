package com.forum.forum.dao;

import com.forum.forum.model.Comment;
import com.forum.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Long> {

    List<Comment> findByUserAndIdBetweenOrderByIdDesc(User user, long start, long end);
}
