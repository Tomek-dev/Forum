package com.forum.forum.service;

import com.forum.forum.dao.ReportDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.ReportInputDto;
import com.forum.forum.dto.ReportOutputDto;
import com.forum.forum.model.Report;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.other.enums.ReportType;
import com.forum.forum.other.enums.Type;
import com.forum.forum.other.exceptions.EnumNotFoundException;
import com.forum.forum.other.exceptions.TopicNotFoundException;
import com.forum.forum.other.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ModelMapper mapper = new ModelMapper();

    private ReportDao reportDao;
    private UserDao userDao;
    private TopicDao topicDao;

    @Autowired
    public ReportService(ReportDao reportDao, UserDao userDao, TopicDao topicDao) {
        this.reportDao = reportDao;
        this.userDao = userDao;
        this.topicDao = topicDao;
    }

    public void addReport(ReportInputDto reportDto, Long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic foundTopic = topicOptional.orElseThrow(TopicNotFoundException::new);
        Report report = new Report.Builder()
                .describe(reportDto.getDescribe())
                .type(ReportType.fromValue(reportDto.getType()).orElseThrow(() -> new EnumNotFoundException("This type not exist.")))
                .topic(foundTopic)
                .build();
        reportDao.save(report);
    }

    public void addReport(ReportInputDto reportDto, String user){
        Optional<User> userOptional = userDao.findByUsernameIgnoreCase(user);
        User foundUser = userOptional.orElseThrow(UserNotFoundException::new);
        Report report = new Report.Builder()
                .describe(reportDto.getDescribe())
                .type(ReportType.fromValue(reportDto.getType()).orElseThrow(() -> new EnumNotFoundException("This type not exist.")))
                .user(foundUser)
                .build();
        reportDao.save(report);
    }

    public List<ReportOutputDto> getPageOfUserReport(Pageable pageable){
        return reportDao.findByUserNotNull(pageable).stream()
                .map(report -> mapper.map(report, ReportOutputDto.class))
                .collect(Collectors.toList());
    }

    public List<ReportOutputDto> getPageOfTopicReport(Pageable pageable){
        return reportDao.findByTopicNotNull(pageable).stream()
                .map(report -> mapper.map(report, ReportOutputDto.class))
                .collect(Collectors.toList());
    }

    public void deleteReportById(Long id){
        reportDao.deleteById(id);
    }
}
