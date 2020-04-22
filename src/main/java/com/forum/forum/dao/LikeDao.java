package com.forum.forum.dao;

import com.forum.forum.model.Like;
import com.forum.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeDao extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndCommentId(User user, Long comment);
}
