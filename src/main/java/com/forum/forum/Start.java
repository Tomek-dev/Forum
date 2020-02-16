package com.forum.forum;

import com.forum.forum.dao.QuestionDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.model.Question;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class Start {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private QuestionDao questionDao;
    private TopicDao topicDao;

    @Autowired
    public Start(UserDao userDao, PasswordEncoder passwordEncoder, QuestionDao questionDao, TopicDao topicDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.questionDao = questionDao;
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
        topic0.setTitle("Wear OS application: Disable all possible options to closing application on android watch\n");
        topic0.setDescription("In this, I need to invoke the application on the android watch from the controller Android application and keep it until it is required to be closed from the controller application. The user should not be able to close the alert application by any means (e.g. swipe to left, long press etc).");
        topic0.setUser(user0);
        user0.getTopics().add(topic0);

        Topic topic1 = new Topic();
        topic1.setCreatedAt(new Date(date - 600000));
        topic1.setTitle("Solutions to 'Some errors have been detected on the server' do not work with my versions");
        topic1.setDescription("The OS is Ubuntu 18.04, the Phpmyadmin is version 4.6.6 and the PHP version is 7.2. I tried both suggested methods (modifying the sql.lib.php or adding to the /etc/phpmyadmin/config.inc.php file but I still get the \"Some errors have been detected' error message (entire viewing screen pink) when I select any table. Ignoring the message things continue as normal. Does this mean I need new Phpmyadmin?");
        topic1.setUser(user1);
        user1.getTopics().add(topic1);

        Topic topic2 = new Topic();
        topic2.setCreatedAt(new Date(date - 300000));
        topic2.setTitle("Best practices in program setup for Arduino communication");
        topic2.setDescription("My current plan: Have the sensors/buttons connected to an Arduino, it sends the data to the android. Android displays and calculates, android sends data to Arduino, Arduino communicates with switches/relays and servos. My current code is, the program start up… The program creates the variables needed based on a “setup config” text file. (the variables that I’ve needed up to this point) Program reads from a “variable values” text file, and sets those values into the variables at start up. It also writes to that same text file any changes to the variables during the execution of the program so that are available at next start up. Program creates GUIs, ActionListeners… JFrames, JButtons, Etc... and displays relevant data from the variables created and initiated during start up methods.");
        topic2.setUser(user2);
        user2.getTopics().add(topic2);

        Topic topic3 = new Topic();
        topic3.setCreatedAt(new Date(date - 540000));
        topic3.setTitle("Why i have this error in symfony, i try but i cant find the error");
        topic3.setDescription("I encounter the following error when injecting services into a controller. I do not know what the error may be attached code. Regards, thanks");
        topic3.setUser(user2);
        user2.getTopics().add(topic3);

        Topic topic4 = new Topic();
        topic4.setCreatedAt(new Date(date-6400000));
        topic4.setTitle("How can I search and access data from a widget which is in a map?");
        topic4.setDescription("I want to create a function which calculates the daily balance. Thus I need to identify all Transaction that have a certain value for the argument date (and maybe put them into a new map) Then I need access to the values from the argument amount (from the valid Transaction that we identified before) and add up all values for the daily balance.");
        topic4.setUser(user4);
        user4.getTopics().add(topic4);

        //QUESTION
        Question question0 = new Question();
        question0.setCreatedAt(new Date(date - 9000));
        question0.setTitle("How to filter data in Flask?");
        question0.setDescription("I'm building advertisement service in Flask and want to let users filter adverts by category but dont know how to do that in python and flask. For example, in home page user click on category 'Motoryzacja' (sorry for Polish words) and flask create subpage: /category_name/ and display adverts only where category='Motoryzacja'.");
        question0.setUser(user2);
        user2.getQuestions().add(question0);

        Question question1 = new Question();
        question1.setCreatedAt(new Date(date - 60000));
        question1.setTitle("How can I setup my Timer to keep it running while my app is in the background?");
        question1.setDescription("So I'm working on my first app, which is a Gym app, and I have a countdown timer setup and when the countdown reaches 0, the end-user would receive a notification that their time has elapsed and they would also hear the sound of a ringing bell. I have all of that setup, but now I'm having trouble figuring out how to keep the timer going when the app is running in the background...");
        question1.setUser(user3);
        user3.getQuestions().add(question1);

        Question question2 = new Question();
        question2.setCreatedAt(new Date(date - 540000));
        question2.setTitle("Organizing a string in rows");
        question2.setDescription("What my current code has : Prompts user to enter number (for example 2) There is a string size I am stuck in getting the program to take the string of 15 and list them 2 in a row in one colum [program is expected to organize the string in my data.txt in rows of 2]");
        question2.setUser(user0);
        user0.getQuestions().add(question2);

        Question question3 = new Question();
        question3.setCreatedAt(new Date(date - 300000));
        question3.setTitle("Partial overlapping of image with color");
        question3.setDescription("I am trying to accomplish this section of a webpage and am having a difficult time finding a good way to overlap the yellow from the 1st column partially over the 2nd column image. I have set up the columns and have tried using negative properties but that didn't work. I tried adding it to the image in Photoshop but that was a BIG fail. I obviously can't do a border hack with opacity.");
        question3.setUser(user0);
        user0.getQuestions().add(question3);

        Question question4 = new Question();
        question4.setCreatedAt(new Date(date - 4700000));
        question4.setTitle("How to scale svg background for react.js app?");
        question4.setDescription("I have a problem with scaling my background svg image. I would expect it to scale to every browser window size, but right now it doesn't scale vertically no matter what params I set. I already tried removing width and height directly in svg file as stated in Why don't my SVG images scale using the CSS \"width\" property? and attempted to adjust viewBox and preserveAspectRatio manually, but the image at some point of scaling always ends up with messed up height (for example 1/3 of the full height for mobile devices). Am I doing something wrong with the image or should I simply create another one for different ratios?");
        question4.setUser(user3);
        user3.getQuestions().add(question4);

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

        questionDao.save(question0);
        questionDao.save(question1);
        questionDao.save(question2);
        questionDao.save(question3);
        questionDao.save(question4);
    }
}
