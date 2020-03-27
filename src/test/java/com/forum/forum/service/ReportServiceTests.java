package com.forum.forum.service;

import com.forum.forum.dao.ReportDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.ReportDto;
import com.forum.forum.enums.ReportType;
import com.forum.forum.enums.Type;
import com.forum.forum.model.Report;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTests {

    @Mock
    private ReportDao reportDao;

    @Mock
    private UserDao userDao;

    @Mock
    private TopicDao topicDao;

    @InjectMocks
    private ReportService reportService;

    @Test(expected = UsernameNotFoundException.class)
    public  void shouldThrowUsernameNotFoundException(){
        //given
        given(userDao.findByUsernameIgnoreCase(Mockito.any())).willReturn(null);

        //when
        reportService.addReport(new Report(), "user");
    }

    @Test
    public void shouldAddUserReport(){
        //given
        User user = new User();
        Report report = new Report(ReportType.OTHER, "describe");
        given(userDao.findByUsernameIgnoreCase(Mockito.any())).willReturn(java.util.Optional.of(user));
        given(reportDao.save(Mockito.any(Report.class))).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        given(userDao.save(Mockito.any(User.class))).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);


        //when
        reportService.addReport(report, "user");

        //then
        verify(userDao).save(user);
        verify(reportDao).save(report);

        assertEquals("describe", reportDao.save(report).getDescribe());
        assertEquals(ReportType.OTHER, reportDao.save(report).getType());
        assertEquals(user, reportDao.save(report).getUser());
        assertEquals(1, userDao.save(user).getReport().size());
    }

    @Test
    public void shouldAddTopicReport(){
        //given
        Topic topic = new Topic();
        Report report = new Report(ReportType.OTHER, "describe");
        given(topicDao.findById(Mockito.any())).willReturn(java.util.Optional.of(topic));
        given(reportDao.save(Mockito.any(Report.class))).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        given(topicDao.save(Mockito.any(Topic.class))).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);


        //when
        reportService.addReport(report, 1L);

        //then
        verify(topicDao).save(topic);
        verify(reportDao).save(report);

        assertEquals("describe", reportDao.save(report).getDescribe());
        assertEquals(ReportType.OTHER, reportDao.save(report).getType());
        assertEquals(topic, reportDao.save(report).getTopic());
        assertEquals(1, topicDao.save(topic).getReport().size());
    }

    @Test
    public void shouldDeleteReport(){
        //when
        reportService.deleteReportById(4L);

        //then
        verify(reportDao).deleteById(4L);
    }
}