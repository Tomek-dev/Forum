package com.forum.forum.service;

import com.forum.forum.dao.ReportDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.ReportDto;
import com.forum.forum.enums.ReportType;
import com.forum.forum.model.Report;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.other.exceptions.TopicNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private ReportDao reportDao;
    private UserDao userDao;
    private TopicDao topicDao;

    @Autowired
    public ReportService(ReportDao reportDao, UserDao userDao, TopicDao topicDao) {
        this.reportDao = reportDao;
        this.userDao = userDao;
        this.topicDao = topicDao;
    }

    public void addReport(Report report, long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic foundTopic = topicOptional.orElseThrow(TopicNotFoundException::new);
        foundTopic.getReport().add(report);
        report.setTopic(foundTopic);
        topicDao.save(foundTopic);
        reportDao.save(report);
    }

    public void addReport(Report report, String user){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(user));
        User foundUser = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        report.setUser(foundUser);
        foundUser.getReport().add(report);
        reportDao.save(report);
        userDao.save(foundUser);
    }

    public List<ReportDto> getPageOf15Report(Pageable pageable){
        return reportDao.findAll(pageable).stream()
                .map(report -> {
                    if(report.getTopic() == null){
                        return new ReportDto(report.getUser().getUsername(), report.getUser().getId(), report.getType().getDisplayName(), new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(report.getCreatedAt()), report.getDescribe());
                    }
                    return new ReportDto(report.getTopic().getTitle(), report.getTopic().getId(), report.getType().getDisplayName(), new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(report.getCreatedAt()), report.getDescribe());
                })
                .collect(Collectors.toList());
    }

    public void deleteReportById(long id){
        reportDao.deleteById(id);
    }
}
