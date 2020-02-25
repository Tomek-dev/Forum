package com.forum.forum.service;

import com.forum.forum.dao.ReportDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.enums.ReportType;
import com.forum.forum.model.Report;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Topic foundTopic = topicOptional.orElseThrow(() -> new RuntimeException("Topic not found"));
        foundTopic.getReport().add(report);
        report.setTopic(foundTopic);
        reportDao.save(report);
        topicDao.save(foundTopic);
    }

    public void addReport(Report report, String user){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(user));
        User foundUser = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        report.setUser(foundUser);
        foundUser.getReport().add(report);
        reportDao.save(report);
        userDao.save(foundUser);
    }


}
