package com.forum.forum.dao;

import com.forum.forum.model.Token;
import com.forum.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Repository
public interface TokenDao extends JpaRepository<Token, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Token token WHERE token.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);

    Token findByUser(User user);
    Token findByToken(UUID token);
}
