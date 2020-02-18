package com.forum.forum;

import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Start {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private TopicDao topicDao;

    @Autowired
    public Start(UserDao userDao, PasswordEncoder passwordEncoder, TopicDao topicDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.topicDao = topicDao;
        load();
    }

    private void load(){
        long date = new Date().getTime();

        //USER
        User user0 = new User("Ciri", "email0@test.test", passwordEncoder.encode("password"), "USER");
        User user1 = new User("Andrew", "email1@test.test", passwordEncoder.encode("password"), "USER");
        User user2 = new User("John", "email2@test.test", passwordEncoder.encode("password"), "USER");
        User user3 = new User("Tommy", "email3@test.test", passwordEncoder.encode("password"), "USER");
        User user4 = new User("Mathew", "email4@test.test", passwordEncoder.encode("password"), "USER");

        //TOPIC
        Topic topic0 = new Topic();
        topic0.setCreatedAt(new Date(date-60000));
        topic0.setTitle("Wear OS application: Disable all possible options to closing application on android watch");
        topic0.setDescription("In this, I need to invoke the application on the android watch from the controller Android application and keep it until it is required to be closed from the controller application. The user should not be able to close the alert application by any means (e.g. swipe to left, long press etc).");
        topic0.setType(Type.CSHARP);
        topic0.setUser(user0);
        user0.getTopics().add(topic0);

        Topic topic1 = new Topic();
        topic1.setCreatedAt(new Date(date - 600000));
        topic1.setTitle("Solutions to 'Some errors have been detected on the server' do not work with my versions");
        topic1.setDescription("The OS is Ubuntu 18.04, the Phpmyadmin is version 4.6.6 and the PHP version is 7.2. I tried both suggested methods (modifying the sql.lib.php or adding to the /etc/phpmyadmin/config.inc.php file but I still get the \"Some errors have been detected' error message (entire viewing screen pink) when I select any table. Ignoring the message things continue as normal. Does this mean I need new Phpmyadmin?");
        topic1.setType(Type.HTMLCSS);
        topic1.setUser(user1);
        user1.getTopics().add(topic1);

        Topic topic2 = new Topic();
        topic2.setCreatedAt(new Date(date - 300000));
        topic2.setTitle("Best practices in program setup for Arduino communication");
        topic2.setDescription("My current plan: Have the sensors/buttons connected to an Arduino, it sends the data to the android. Android displays and calculates, android sends data to Arduino, Arduino communicates with switches/relays and servos. My current code is, the program start up… The program creates the variables needed based on a “setup config” text file. (the variables that I’ve needed up to this point) Program reads from a “variable values” text file, and sets those values into the variables at start up. It also writes to that same text file any changes to the variables during the execution of the program so that are available at next start up. Program creates GUIs, ActionListeners… JFrames, JButtons, Etc... and displays relevant data from the variables created and initiated during start up methods.");
        topic2.setType(Type.PYTHON);
        topic2.setUser(user2);
        user2.getTopics().add(topic2);

        Topic topic3 = new Topic();
        topic3.setCreatedAt(new Date(date - 540000));
        topic3.setTitle("Why i have this error in symfony, i try but i cant find the error");
        topic3.setDescription("I encounter the following error when injecting services into a controller. I do not know what the error may be attached code. Regards, thanks");
        topic3.setType(Type.C);
        topic3.setUser(user2);
        user2.getTopics().add(topic3);

        Topic topic4 = new Topic();
        topic4.setCreatedAt(new Date(date-6400000));
        topic4.setTitle("How can I search and access data from a widget which is in a map?");
        topic4.setDescription("I want to create a function which calculates the daily balance. Thus I need to identify all Transaction that have a certain value for the argument date (and maybe put them into a new map) Then I need access to the values from the argument amount (from the valid Transaction that we identified before) and add up all values for the daily balance.");
        topic4.setType(Type.JAVA);
        topic4.setUser(user4);
        user4.getTopics().add(topic4);

        //SAVE
        userDao.save(user0);
        userDao.save(user1);
        userDao.save(user2);
        userDao.save(user3);
        userDao.save(user4);

        topicDao.save(topic0);
        topicDao.save(topic1);
        topicDao.save(topic2);
        topicDao.save(topic3);
        topicDao.save(topic4);
    }
}
