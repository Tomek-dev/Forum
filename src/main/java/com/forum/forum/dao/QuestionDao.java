package com.forum.forum.dao;

import com.forum.forum.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Long>{
    List<Question> findTop4ByOrderByIdDesc();
}
