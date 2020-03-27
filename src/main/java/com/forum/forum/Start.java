package com.forum.forum;

import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.enums.Type;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.other.builder.TopicBuilder;
import com.forum.forum.other.builder.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class Start {

    private static final String[] USERNAME= {"Ciri", "Andrew", "John", "Tommy", "Farhad", "Mohammed", "Filip", "Zion", "Omar", "Michael"};
    private static final ChronoUnit[] UNIT = {ChronoUnit.MINUTES, ChronoUnit.HOURS, ChronoUnit.DAYS, ChronoUnit.SECONDS, ChronoUnit.MONTHS};
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private TopicDao topicDao;
    private User[] user = new User[10];
    private LocalDateTime date;
    private List<Topic> topics = new LinkedList<>();

    @Autowired
    public Start(UserDao userDao, PasswordEncoder passwordEncoder, TopicDao topicDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.topicDao = topicDao;
        date = LocalDateTime.now();
        loadUser();
        loadTopic();
    }

    private void loadUser(){
        for (int i = 0; i < user.length; i++) {
            user[i] = UserBuilder.builder()
                    .username(USERNAME[i])
                    .email("email" + i + "@test.test")
                    .password(passwordEncoder.encode("password"))
                    .roles(Collections.singleton("USER"))
                    .motto("\"coding\" Makes you smartest, because all can't do it all")
                    .createdAt(date)
                    .build();
        }
        User admin = UserBuilder.builder()
                .username("admin")
                .email("email")
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton("ADMIN"))
                .build();
        userDao.save(admin);
        userDao.saveAll(Arrays.asList(user));
    }

    private void loadTopic(){
        Random random = new Random();
        for (Type value : Type.values()) {
            for (int i = 0; i < 15; i++) {
                int id = random.nextInt(10);
                Topic topic = TopicBuilder.builder()
                        .user(user[id])
                        .type(value)
                        .title("Type is " + value)
                        .description("Description is " + value)
                        .createdAt(date.minus(random.nextInt(10), UNIT[random.nextInt(5)]))
                        .build();
                user[id].getTopics().add(topic);
                topics.add(topic);
            }
        }
        topicDao.saveAll(topics);
    }
}
