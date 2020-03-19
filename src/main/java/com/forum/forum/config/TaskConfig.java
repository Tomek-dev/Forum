package com.forum.forum.config;

import com.forum.forum.dao.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Configuration
@EnableScheduling
public class TaskConfig {

    @Autowired
    private TokenDao tokenDao;

    @Scheduled(fixedRate = 60000)
    public void purgeTask(){
        LocalDateTime now = LocalDateTime.now();
        tokenDao.deleteByExpiryDateLessThanEqual(now);
    }
}
