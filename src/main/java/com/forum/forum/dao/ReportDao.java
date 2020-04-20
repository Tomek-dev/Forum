package com.forum.forum.dao;

import com.forum.forum.dto.ReportOutputDto;
import com.forum.forum.model.HelpMessage;
import com.forum.forum.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDao extends JpaRepository<Report, Long> {
    Page<Report> findAll(Pageable pageable);
    List<Report> findByUserNotNull(Pageable pageable);
    List<Report> findByTopicNotNull(Pageable pageable);
}
