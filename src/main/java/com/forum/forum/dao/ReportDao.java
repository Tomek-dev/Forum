package com.forum.forum.dao;

import com.forum.forum.model.HelpMessage;
import com.forum.forum.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDao extends JpaRepository<Report, Long> {
    Page<Report> findAll(Pageable pageable);
}
