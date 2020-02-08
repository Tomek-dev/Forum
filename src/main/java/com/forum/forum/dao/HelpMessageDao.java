package com.forum.forum.dao;

import com.forum.forum.model.HelpMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpMessageDao extends JpaRepository<HelpMessage, Long> {

}
