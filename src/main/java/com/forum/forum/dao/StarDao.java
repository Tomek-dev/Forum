package com.forum.forum.dao;

import com.forum.forum.model.Star;
import com.forum.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StarDao extends JpaRepository<Star, Long> {
    Optional<Star> findByStarUserAndUser(User starUser, User user);
}
