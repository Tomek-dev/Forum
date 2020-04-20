package com.forum.forum.service;

import com.forum.forum.dao.ReportDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.ReportInputDto;
import com.forum.forum.other.enums.ReportType;
import com.forum.forum.model.Report;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.other.exceptions.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

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

    @Test(expected = UserNotFoundException.class)
    public  void shouldThrowUsernameNotFoundException(){
        //given
        given(userDao.findByUsernameIgnoreCase(Mockito.any())).willReturn(Optional.empty());

        //when
        reportService.addReport(new ReportInputDto(), "user");
    }

    @Test
    public void shouldAddUserReport(){
        //given
        User user = new User();
        ReportInputDto reportDto = new ReportInputDto("other", "describe");
        given(userDao.findByUsernameIgnoreCase(Mockito.any())).willReturn(java.util.Optional.of(user));
        given(reportDao.save(Mockito.any(Report.class))).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        given(userDao.save(Mockito.any(User.class))).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);


        //when
        reportService.addReport(reportDto, "user");

        //then
        verify(reportDao).save(any());

        assertEquals(1, user.getReport().size());
    }

    @Test
    public void shouldAddTopicReport(){
        //given
        Topic topic = new Topic();
        ReportInputDto reportDto = new ReportInputDto("other", "describe");
        given(topicDao.findById(Mockito.any())).willReturn(java.util.Optional.of(topic));

        //when
        reportService.addReport(reportDto, 1L);

        //then
        verify(reportDao).save(any());
    }

    @Test
    public void shouldDeleteReport(){
        //when
        reportService.deleteReportById(4L);

        //then
        verify(reportDao).deleteById(4L);
    }
}