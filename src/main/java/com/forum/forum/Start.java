package com.forum.forum;

import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.enums.Type;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class Start {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private TopicDao topicDao;
    private User[] user = new User[10];

    @Autowired
    public Start(UserDao userDao, PasswordEncoder passwordEncoder, TopicDao topicDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.topicDao = topicDao;
        loadUser();
        loadJavaTopic();
        loadCPPTopic();
        loadCSharpTopic();
        loadCTopic();
        loadHTMLCSSTopic();
        loadJavaScriptTopic();
        loadOtherTopic();
        loadPHPTopic();
        loadPythonTopic();
        loadRubyTopic();
        loadSQLTopic();
        loadSwiftTopic();
    }

    private void loadUser(){
        user[0] = new User("Ciri", "email0@test.test", passwordEncoder.encode("password"), "USER");
        user[1] = new User("Andrew", "email1@test.test", passwordEncoder.encode("password"), "USER");
        user[2] = new User("John", "email2@test.test", passwordEncoder.encode("password"), "USER");
        user[3] = new User("Tommy", "email3@test.test", passwordEncoder.encode("password"), "USER");
        user[4] = new User("Farhad", "email4@test.test", passwordEncoder.encode("password"), "USER");
        user[5] = new User("Mohammed", "email5@test.test", passwordEncoder.encode("password"), "USER");
        user[6] = new User("Filip", "email6@test.test", passwordEncoder.encode("password"), "USER");
        user[7] = new User("Zion", "email7@test.test", passwordEncoder.encode("password"), "USER");
        user[8] = new User("Omar", "email8@test.test", passwordEncoder.encode("password"), "USER");
        user[9] = new User("Michael", "email9@test.test", passwordEncoder.encode("password"), "USER");

        userDao.saveAll(Arrays.asList(user));
    }



    private void loadJavaTopic(){
        long date = new Date().getTime();
        Topic[] javaTopics = new Topic[10];

        javaTopics[0] = new Topic();
        javaTopics[0].setUser(user[0]);
        user[0].getTopics().add(javaTopics[0]);
        javaTopics[0].setType(Type.JAVA);
        javaTopics[0].setCreatedAt(new Date(date-60000));
        javaTopics[0].setTitle("How to get streaming data from dolphindb with java api？");
        javaTopics[0].setDescription("I am making a streaming data testing in a dolphindb cluster , there is a streaming table in a data node, i subscribed the streaming data with java api but no data received. i use ThreadedClient to subscribe streamTable and no exceptions, could anyone give a hand?");

        javaTopics[1] = new Topic();
        javaTopics[1].setUser(user[3]);
        user[3].getTopics().add(javaTopics[1]);
        javaTopics[1].setType(Type.JAVA);
        javaTopics[1].setCreatedAt(new Date(date-540000));
        javaTopics[1].setTitle("Data not saved in db when the time @PostPersist got called");
        javaTopics[1].setDescription("i need to send a request to other microservice once the object got created in the database. i only send the object id so other microservice needs to call the db again for the info with bunch of other stuff.\n" +
                "\n" +
                "but when the other microservice try to lookup for the record using the received id it cannot find the saved record in the database.\n" +
                "\n" +
                "I tried debug seems like record does not persist even though @postPersist got called. It will be saved after @PostPersist got executed.\n" +
                "\n" +
                "Has anyone could give a workaround for this. I really need to query the database again as this is a custom requirement. I use mysql and spring boot");

        javaTopics[2] = new Topic();
        javaTopics[2].setUser(user[6]);
        user[6].getTopics().add(javaTopics[2]);
        javaTopics[2].setType(Type.JAVA);
        javaTopics[2].setCreatedAt(new Date(date-340000));
        javaTopics[2].setTitle("Add Lombok to a Gradle project in Intellij");
        javaTopics[2].setDescription("New to Gradle. I Apologize if this is a bad question.\n" +
                "\n" +
                "I've been trying to start a plain Java8 Gradle project. Everything was great until I tried to add Lombok to my project.\n" +
                "\n" +
                "My Intellij IDE has all the settings required for Lombok.\n" +
                "\n" +
                "Lombok plugin installed and activated\n" +
                "Annotation processor is on\n" +
                "The IDE is handling the annotations perfectly. No errors showing, and the boilerplate is shown in the code structure.\n" +
                "\n" +
                "However, when I try to build the project or run the main method using Intellij, the compiler complains that the boilerplate Lombok is supposed to generate doesn't exist.");

        javaTopics[3] = new Topic();
        javaTopics[3].setUser(user[9]);
        user[9].getTopics().add(javaTopics[3]);
        javaTopics[3].setType(Type.JAVA);
        javaTopics[3].setCreatedAt(new Date(date-14000));
        javaTopics[3].setTitle("Spring @valid annotation cannot be resolved");
        javaTopics[3].setDescription("I am trying to create a simple JPA framework that performs simple CRUD operations like save employees, delete employees or get a simple employee.");

        javaTopics[4] = new Topic();
        javaTopics[4].setUser(user[4]);
        user[4].getTopics().add(javaTopics[4]);
        javaTopics[4].setType(Type.JAVA);
        javaTopics[4].setCreatedAt(new Date(date-170000));
        javaTopics[4].setTitle("Applying foreground color / highlight to specific rows of the excel using Apache POI");
        javaTopics[4].setDescription("Am copying data/ sheet from one excel workbook to another workbook using copyRow. I want to apply foreground color to specific rows in my target excel. Below is the code i have now. Please help how should i do it. Found the link for Cell Style from POI's quick guide. But please help to use it to apply it to specific rows in the target excel.");

        javaTopics[5] = new Topic();
        javaTopics[5].setUser(user[2]);
        user[2].getTopics().add(javaTopics[5]);
        javaTopics[5].setType(Type.JAVA);
        javaTopics[5].setCreatedAt(new Date(date-78000));
        javaTopics[5].setTitle("Elasticsearch composite group by queries across the documents");
        javaTopics[5].setDescription("We have an elastic search document which has a dimension called city. Each document will have only one value for city field. I have a scenario where I need to query the person based on the city or cities.");

        javaTopics[6] = new Topic();
        javaTopics[6].setUser(user[7]);
        user[7].getTopics().add(javaTopics[6]);
        javaTopics[6].setType(Type.JAVA);
        javaTopics[6].setCreatedAt(new Date(date-600000));
        javaTopics[6].setTitle("How to use internationalization in struts2 for dropdownlist");
        javaTopics[6].setDescription("By using struts2 localization how to change dropdowmlist value english to hindi");

        javaTopics[7] = new Topic();
        javaTopics[7].setUser(user[1]);
        user[1].getTopics().add(javaTopics[7]);
        javaTopics[7].setType(Type.JAVA);
        javaTopics[7].setCreatedAt(new Date(date-650000));
        javaTopics[7].setTitle("Setting objects as partners within the same class in java");
        javaTopics[7].setDescription("As a task in my beginners course in object oriented programming, I must set two obects in my Partner class as \"married\". This is my attempt at beginning: public class Partner {");

        javaTopics[8] = new Topic();
        javaTopics[8].setUser(user[8]);
        user[8].getTopics().add(javaTopics[8]);
        javaTopics[8].setType(Type.JAVA);
        javaTopics[8].setCreatedAt(new Date(date-120000));
        javaTopics[8].setTitle("Logging Request and Response BODY of Spring WebClient");
        javaTopics[8].setDescription("I would like to log all request and response text payload of WebClient used in a Spring WebFlux project and have gone through a few solutions.\n" +
                "\n" +
                "One of them change implementation of HttpClient, like jetty httpclient and customise it. One of them change netty client logging level and update the error logging handler, also get other verbose logs from it.\n" +
                "\n" +
                "Similar answers are posted in stackoverflow, but it just log the headers. Most people suggest use ExchangeFilter.\n" +
                "\n" +
                "Therefore, I implement Request and Response exchange filter as below and set it to my WebClient. Can some one explain in more details why it forfeit WebFlux reactive's goodness?\n" +
                "\n" +
                "For response, it seems quite easy because I can stream the body and make a new BodyExtractor and make a new ClientResponse and continue on the reactive stream.\n" +
                "\n" +
                "For request, I have to customise ClientHttpRequest to write body from a BodyInserter. Basically, it clone the ClientHttpRequest and trigger a customised writing process. I considered doing this in the Jackson encoder, but it touches so deep in the implementation, and reckon customise ClientHttpRequest is easier.\n" +
                "\n" +
                "Codes are in Kotlin, but it should not be difficult to understood by Java developers.\n" +
                "\n" +
                "Thanks in advance to any comments.");

        javaTopics[9] = new Topic();
        javaTopics[9].setUser(user[0]);
        user[0].getTopics().add(javaTopics[9]);
        javaTopics[9].setType(Type.JAVA);
        javaTopics[9].setCreatedAt(new Date(date-40000));
        javaTopics[9].setTitle("The module has not been deployed.- RESTFUL WEB SERVICE - GLASSFISH SERVER 4.0");
        javaTopics[9].setDescription("I am trying to create simple restful webservice in netbeans. Everything is going well until I connect my webservice to JDBC and is giving me some runtime exception. I've been using Netbeans 8.0.2 with glassfish 4.0. does anyone encounter the same problem?\n" +
                "\n" +
                "I have tried task manager-> proccess->end process Java SE but no to avail.");

        topicDao.saveAll(Arrays.asList(javaTopics));
        userDao.saveAll(Arrays.asList(user));
    }

    private void loadPythonTopic(){
        long date = new Date().getTime();
        Topic[] pythonTopics = new Topic[10];

        pythonTopics[0] = new Topic();
        pythonTopics[0].setUser(user[4]);
        user[4].getTopics().add(pythonTopics[0]);
        pythonTopics[0].setType(Type.PYTHON);
        pythonTopics[0].setCreatedAt(new Date(date-60000));
        pythonTopics[0].setTitle("How to use SQL statements parameters compatible with sqlite and postgresql with python?");
        pythonTopics[0].setDescription("in which you want to insert, using bound parameters in python. Is there a simple or elegant way to do this? Modules sqlite3 and psycopg2 seem to be using non-overlapping placeholders:\n" +
                "\n" +
                "psycopg2: %s and %(name)s\n" +
                "sqlite3: ? and :name\n" +
                "I looked into psycopg2's mogrify, but it seems like it produces postgresql-only valid statements.\n" +
                "\n" +
                "I don't care for a \"works in all circumstances\" solution, just for a simple way to have string and numerical bound SQL parameters using simple SELECT/INSERT/UPDATE/DELETE statements.");

        pythonTopics[1] = new Topic();
        pythonTopics[1].setUser(user[6]);
        user[6].getTopics().add(pythonTopics[1]);
        pythonTopics[1].setType(Type.PYTHON);
        pythonTopics[1].setCreatedAt(new Date(date-540000));
        pythonTopics[1].setTitle("nput[type=submit] in python urllib3");
        pythonTopics[1].setDescription("I want to fill this form and send it. With input type text, I can manage it but This input button have type submit. Using Python urllib3, How to click this button ?");

        pythonTopics[2] = new Topic();
        pythonTopics[2].setUser(user[9]);
        user[9].getTopics().add(pythonTopics[2]);
        pythonTopics[2].setType(Type.PYTHON);
        pythonTopics[2].setCreatedAt(new Date(date-2360000));
        pythonTopics[2].setTitle("Why are some cdef's accessible and some aren't when accessing a Cython binary?");
        pythonTopics[2].setDescription("I read that cdefed objects are not visible from Python, why can I see `Qos?\n" +
                "\n" +
                "What I would really like to do is to access the also cdefed attribute Qos._c_qos or Qos._policies from instances of Qos, but they are not available.\n" +
                "\n" +
                "Is there a difference between these cdefs? Is there a setting on which cdefs are exposed and which are not?");

        pythonTopics[3] = new Topic();
        pythonTopics[3].setUser(user[3]);
        user[3].getTopics().add(pythonTopics[3]);
        pythonTopics[3].setType(Type.PYTHON);
        pythonTopics[3].setCreatedAt(new Date(date-1230000));
        pythonTopics[3].setTitle("Can't run a defined function in a Python class");
        pythonTopics[3].setDescription("I'm making an app for a photobooth and to test the functions, I called the function called takephoto just after building the interface in the code, but the console has no printed TESTING. What can be causing the issue to appear?");

        pythonTopics[4] = new Topic();
        pythonTopics[4].setUser(user[1]);
        user[1].getTopics().add(pythonTopics[4]);
        pythonTopics[4].setType(Type.PYTHON);
        pythonTopics[4].setCreatedAt(new Date(date-14000));
        pythonTopics[4].setTitle("find average upto x number of rows of particular column in dataframe");
        pythonTopics[4].setDescription("I have data in dataframe like the one shown below, data is coming from a website.\n" +
                "\n" +
                "Difference column is data-7.3\n" +
                "\n" +
                "Day is nothing but serial number starting from 1\n" +
                "\n" +
                "index is average of rows in difference upto the current row multiplied by day. for example,\n" +
                "\n" +
                "First row in index will have average(0.19)*1\n" +
                "\n" +
                "Second row in index will be average(0.19,0.19)*2\n" +
                "\n" +
                "Third row in index will be average(0.19,0.19,0.17)*3");

        pythonTopics[5] = new Topic();
        pythonTopics[5].setUser(user[6]);
        user[6].getTopics().add(pythonTopics[5]);
        pythonTopics[5].setType(Type.PYTHON);
        pythonTopics[5].setCreatedAt(new Date(date-70000));
        pythonTopics[5].setTitle("Extract Twitter account description Scraping\n");
        pythonTopics[5].setDescription("I want to extract the text that gives a description of a Twitter account, but I can't access, all I have achieved is extract the number of followers, but I can't know how to get to the text that describes the Twitter account.\n" +
                "\n" +
                "I print the number of followers, but I can't print the description account.");

        pythonTopics[6] = new Topic();
        pythonTopics[6].setUser(user[8]);
        user[8].getTopics().add(pythonTopics[6]);
        pythonTopics[6].setType(Type.PYTHON);
        pythonTopics[6].setCreatedAt(new Date(date-60000));
        pythonTopics[6].setTitle("python: add subelement tag in xml file");
        pythonTopics[6].setDescription("I have an xml file containing the example of data below. I have to rename the tag db to Id_DB, x1 to x11, add a tag x44 that will contain the data of x2 and then rename the tag x2 to x22. I did the code below. But it does not do what I want. 'x44' is always empty. Can anyone help me to correct it, please?");

        pythonTopics[7] = new Topic();
        pythonTopics[7].setUser(user[0]);
        user[0].getTopics().add(pythonTopics[7]);
        pythonTopics[7].setType(Type.PYTHON);
        pythonTopics[7].setCreatedAt(new Date(date-12000));
        pythonTopics[7].setTitle("Part wise Gram-Schmidt-Scheme on a large set of Vectors");
        pythonTopics[7].setDescription("In a Simulation i create a large set of vectors on which i apply the Gram-Schmidt scheme part wise. By part wise i mean, that i first remove the components of the vectors and then compute its length (check orth_2 or orth_3 formulas in the code below). The length is then stored to compute later a desired value (l-values, see code below). Only after that i normalize the vector.\n" +
                "\n" +
                "The above explained procedure is shown in the following code for three vectors:");

        pythonTopics[8] = new Topic();
        pythonTopics[8].setUser(user[5]);
        user[5].getTopics().add(pythonTopics[8]);
        pythonTopics[8].setType(Type.PYTHON);
        pythonTopics[8].setCreatedAt(new Date(date-3480000));
        pythonTopics[8].setTitle("How to connect django with mysql database proberly");
        pythonTopics[8].setDescription("0\n" +
                "\n" +
                "\n" +
                "First of all im tring to connect a django_project to mysql by editting my_project/settings.py file with the code below\n" +
                "I created a database called (mydatabase) and user with full privileges called (admin) identified by (admin), then i tried to connect my django project with mydatabase by the following code below:");

        pythonTopics[9] = new Topic();
        pythonTopics[9].setUser(user[2]);
        user[2].getTopics().add(pythonTopics[9]);
        pythonTopics[9].setType(Type.PYTHON);
        pythonTopics[9].setCreatedAt(new Date(date-1420000));
        pythonTopics[9].setTitle("Airflow smtp env variables not being detected while defined on interface");
        pythonTopics[9].setDescription("If I configure smtp configurations in airflow.cfg, sending email works, but when I define them on the webserver interface Admin -> Variable section and comment out airflow.cfg following by restarting containers, it complains about not finding config while worker has access to them eiher by printing variable's value in a task (by checking task log) or by querying directly from console:");

        topicDao.saveAll(Arrays.asList(pythonTopics));
        userDao.saveAll(Arrays.asList(user));
    }

    private void loadCPPTopic(){
        long date = new Date().getTime();
        Topic[] cppTopics = new Topic[10];

        cppTopics[0] = new Topic();
        cppTopics[0].setUser(user[0]);
        user[0].getTopics().add(cppTopics[0]);
        cppTopics[0].setType(Type.CPP);
        cppTopics[0].setCreatedAt(new Date(date-60000));
        cppTopics[0].setTitle("C++: Getting an unresolved external symbol while it is defined");
        cppTopics[0].setDescription("So I'm writing an Input handler in C++ (and GLFW, but that doesn't matter), and the functions that are not implemented in the header drop these errors:");

        cppTopics[1] = new Topic();
        cppTopics[1].setUser(user[1]);
        user[1].getTopics().add(cppTopics[1]);
        cppTopics[1].setType(Type.CPP);
        cppTopics[1].setCreatedAt(new Date(date-540000));
        cppTopics[1].setTitle("Data type modifiers");
        cppTopics[1].setDescription("In a C++ programming why does int long long unsigned x; not produce any syntax error as unsigned and long long are modifiers and must be written before the data type ?");

        cppTopics[2] = new Topic();
        cppTopics[2].setUser(user[9]);
        user[9].getTopics().add(cppTopics[2]);
        cppTopics[2].setType(Type.CPP);
        cppTopics[2].setCreatedAt(new Date(date-2360000));
        cppTopics[2].setTitle("UE4 C++ code has red underline but compile fine [closed]");
        cppTopics[2].setDescription("It's my first UE4 C++ code, I just followed the documentation tutorial the code\n" +
                "\n" +
                "It compile fine, the result please help fixing this problem. Thank you!");

        cppTopics[3] = new Topic();
        cppTopics[3].setUser(user[7]);
        user[7].getTopics().add(cppTopics[3]);
        cppTopics[3].setType(Type.CPP);
        cppTopics[3].setCreatedAt(new Date(date-1230000));
        cppTopics[3].setTitle("How to understand that convert half-precision pointer to unsigned long long pointer and relevant memory alignment?");
        cppTopics[3].setDescription("I am not familiar with the problems about memory alignment and pointer conversion. I am learning from the Nvidia official sample code as the following.");

        cppTopics[4] = new Topic();
        cppTopics[4].setUser(user[8]);
        user[8].getTopics().add(cppTopics[4]);
        cppTopics[4].setType(Type.CPP);
        cppTopics[4].setCreatedAt(new Date(date-14000));
        cppTopics[4].setTitle("Returning all possible unique triplets that sums to 0");
        cppTopics[4].setDescription("I am new to C++. I want to return all possible unique triplets of a vector that sums to 0 in a vector of vector. But when I try to compile below code snippet it gives me following error: C:\\Users\\User\\Documents\\C++\\3_sum.cpp In function 'std::vector > FindPerm(std::vector >&, std::vector, std::set, int, int, int, int)': 14 83 C:\\Users\\User\\Documents\\C++\\3_sum.cpp [Error] invalid use of void expression.\n" +
                "\n" +
                "In my code the arr vector is initially empty. In each recursive call i am considering a particular element of the set, followed by two recursive call including and excluding that element.all the unique triplets needs to be stored at the vector vect, hence i am passing it by reference. Below is the detailed code snippet.");

        cppTopics[5] = new Topic();
        cppTopics[5].setUser(user[3]);
        user[3].getTopics().add(cppTopics[5]);
        cppTopics[5].setType(Type.CPP);
        cppTopics[5].setCreatedAt(new Date(date-70000));
        cppTopics[5].setTitle("How does one Iterate through the elements of an Extendible Hashing structure (Set, specifically)");
        cppTopics[5].setDescription("I have written an implementation of an Extendible Hashing Set in C++. I've used Arrays to achieve it and everything seems to be fine working fine. The bucket structure is simple");

        cppTopics[6] = new Topic();
        cppTopics[6].setUser(user[4]);
        user[4].getTopics().add(cppTopics[6]);
        cppTopics[6].setType(Type.CPP);
        cppTopics[6].setCreatedAt(new Date(date-60000));
        cppTopics[6].setTitle("Examples of Pypy being too slow for competitive programming [closed]");
        cppTopics[6].setDescription("Are there any instances of competitive programming problems that can only be solved using C++ but not Pypy? (assuming both are supported in that site / competition).");

        cppTopics[7] = new Topic();
        cppTopics[7].setUser(user[1]);
        user[1].getTopics().add(cppTopics[7]);
        cppTopics[7].setType(Type.CPP);
        cppTopics[7].setCreatedAt(new Date(date-12000));
        cppTopics[7].setTitle("Apparently unjustified time measurements when usleep() is present");
        cppTopics[7].setDescription("I am using the C program below (compiled with gcc) to measure the computing time of a given code section. The issue is that when uleep() function is used outside the code section being profiled, different measurements are obtained in elapsed_time.");

        cppTopics[8] = new Topic();
        cppTopics[8].setUser(user[7]);
        user[7].getTopics().add(cppTopics[8]);
        cppTopics[8].setType(Type.CPP);
        cppTopics[8].setCreatedAt(new Date(date-3480000));
        cppTopics[8].setTitle("cmake configuration failed while building MRPT library");
        cppTopics[8].setDescription("I am trying to install MRPT, especially commit 0c3d605c3c recommended in PL-SLAM documentation; however, I am consistently getting cmake error while building the repo. Is there any way to resolve this issue? Your help would be highly appreciable.");

        cppTopics[9] = new Topic();
        cppTopics[9].setUser(user[9]);
        user[9].getTopics().add(cppTopics[9]);
        cppTopics[9].setType(Type.CPP);
        cppTopics[9].setCreatedAt(new Date(date-1420000));
        cppTopics[9].setTitle("Poco C++ Android JNI");
        cppTopics[9].setDescription("I am not exactly sure if I understand it correctly, so I want to optimize the network requests a bit for my android application and used so far OkhttpClient, HttpUrlConnection, and Java Sockets but am not happy with the performance. I found a nice C++ library called Poco and thought I would give it a try so I cross compile it for the different ABIs. So far so good nowhere is the point I am not sure about, do I need to write my own JNI wrapper for poco so that I can use these libraries because I mean to compile it only for the different ABIs doesn’t mean I can use it right out of the box, or? And what is the best practice to do so? Do I need to add the JNI let’s call it method annotations to the .cpp files to use it?\n" +
                "\n" +
                "Thanks in advance");

        topicDao.saveAll(Arrays.asList(cppTopics));
        userDao.saveAll(Arrays.asList(user));
    }

    private void loadCSharpTopic(){
        long date = new Date().getTime();
        Topic[] CSharpTopics = new Topic[10];

        CSharpTopics[0] = new Topic();
        CSharpTopics[0].setUser(user[1]);
        user[1].getTopics().add(CSharpTopics[0]);
        CSharpTopics[0].setType(Type.CSHARP);
        CSharpTopics[0].setCreatedAt(new Date(date-60000));
        CSharpTopics[0].setTitle(".Net linebot isRock.LineBot.Utility.PushMessage doesn't work");
        CSharpTopics[0].setDescription("I built a linebot with LineBotSDK and deployed on Azure application. It ran nice before but when I revise some code and push it today, I found isRock.LineBot.Utility.PushMessage() can't be used and with no exception. I've reinstall the API using Nuget but still can't resolve this problem. Is there any solution to deal with it?\n" +
                "\n" +
                "Please help me, thank you!");

        CSharpTopics[1] = new Topic();
        CSharpTopics[1].setUser(user[0]);
        user[0].getTopics().add(CSharpTopics[1]);
        CSharpTopics[1].setType(Type.CSHARP);
        CSharpTopics[1].setCreatedAt(new Date(date-540000));
        CSharpTopics[1].setTitle("MSBuild looks for Main when building a project");
        CSharpTopics[1].setDescription("I am working on a large project that consists of multiple executables. I'd like to automate deployment process by building all of them at once. That is why I resorted to msbuild command line utility.\n" +
                "\n" +
                "When I build one of my projects in Visual Studio it build normally. When I try to do the same using msbuild cmd, it fails with an error");

        CSharpTopics[2] = new Topic();
        CSharpTopics[2].setUser(user[3]);
        user[3].getTopics().add(CSharpTopics[2]);
        CSharpTopics[2].setType(Type.CSHARP);
        CSharpTopics[2].setCreatedAt(new Date(date-2360000));
        CSharpTopics[2].setTitle("C# BLE Read and Write Windows PC application\n" +
                "Ask Question");
        CSharpTopics[2].setDescription("We need BLE send and receive BLE client windows pc application example how to get write and read ?");

        CSharpTopics[3] = new Topic();
        CSharpTopics[3].setUser(user[2]);
        user[2].getTopics().add(CSharpTopics[3]);
        CSharpTopics[3].setType(Type.CSHARP);
        CSharpTopics[3].setCreatedAt(new Date(date-1230000));
        CSharpTopics[3].setTitle("Visual studio 2017/19 Intellisense for vb6 COM dll");
        CSharpTopics[3].setDescription("I am exploring the possibility of using old VB6 built dlls that contain certain business logic in a C#.NET project. I can successfully add the dll as a reference once I use TlbImp.exe, and reference the resulting outputs, however while writing code, I get no intellisense for the namespace or any types defined within the library. Object explorer does show the content of the library. Is there some specific procedure for seeing the contents of the dll in code, or would the dll have to be specifically prepared for such a use beforehand(such as specific interfaces or such)?");

        CSharpTopics[4] = new Topic();
        CSharpTopics[4].setUser(user[7]);
        user[7].getTopics().add(CSharpTopics[4]);
        CSharpTopics[4].setType(Type.CSHARP);
        CSharpTopics[4].setCreatedAt(new Date(date-14000));
        CSharpTopics[4].setTitle("Need little help regarding data structures etc");
        CSharpTopics[4].setDescription("actually i am quite new to C# so i wanted little help from you guys. i just want to know what logic should i use to implement the following logic i mean what data structure etc should i use? Problem: I am implementing a airplane registration/management system and i am stuck at this point i just want to know what data structure would be the best option to implement the following problem\n" +
                "\n" +
                "1) System should allow user to enter his information such as, Name , Identity Number/SSN for reservation. 2) System should allow user to show the current status of reserved and non-reserved seats. 3) User is allowed to reserve non-reserve seat;");

        CSharpTopics[5] = new Topic();
        CSharpTopics[5].setUser(user[8]);
        user[8].getTopics().add(CSharpTopics[5]);
        CSharpTopics[5].setType(Type.CSHARP);
        CSharpTopics[5].setCreatedAt(new Date(date-70000));
        CSharpTopics[5].setTitle(".Net Core 3.1 react application with identity authentication - 403 Error Access is denied using provided credentials");
        CSharpTopics[5].setDescription("This error is misleading. I never had the opportunity to enter my login credentials because the login page didn't render. I created this project in Visual Studio 2019 with react and individual user account authentication. How can i make the login page the first to render?");

        CSharpTopics[6] = new Topic();
        CSharpTopics[6].setUser(user[5]);
        user[5].getTopics().add(CSharpTopics[6]);
        CSharpTopics[6].setType(Type.CSHARP);
        CSharpTopics[6].setCreatedAt(new Date(date-60000));
        CSharpTopics[6].setTitle("Byte Array to Struct UDP Packet");
        CSharpTopics[6].setDescription("I am currently stuck with my project. I currently recieve UDP packets from a game. These packets contain byte arrays. These byte arrays need to be converted to a struct.\n" +
                "\n" +
                "I've tackled a previous problem in this question: F1 2019 UDP decoding\n" +
                "\n" +
                "I have got some part of this to work. I use this code to convert the byte array in to a struct:");

        CSharpTopics[7] = new Topic();
        CSharpTopics[7].setUser(user[1]);
        user[1].getTopics().add(CSharpTopics[7]);
        CSharpTopics[7].setType(Type.CSHARP);
        CSharpTopics[7].setCreatedAt(new Date(date-12000));
        CSharpTopics[7].setTitle("How to add image or background image to custom button in winforms?");
        CSharpTopics[7].setDescription("i am pretty new at making custom controls. I have tried to create a custom button but i can not add background image or image to my button at properties. I don't know why. I have search about this i did not find a solution now asking to you.");

        CSharpTopics[8] = new Topic();
        CSharpTopics[8].setUser(user[0]);
        user[0].getTopics().add(CSharpTopics[8]);
        CSharpTopics[8].setType(Type.CSHARP);
        CSharpTopics[8].setCreatedAt(new Date(date-3480000));
        CSharpTopics[8].setTitle("how to change properties of an object that wasn't created dynamically asp.net");
        CSharpTopics[8].setDescription("Hello I'm trying to change properties of an object I created in the aspx page with my code. I want something that will work something like this");

        CSharpTopics[9] = new Topic();
        CSharpTopics[9].setUser(user[4]);
        user[4].getTopics().add(CSharpTopics[9]);
        CSharpTopics[9].setType(Type.CSHARP);
        CSharpTopics[9].setCreatedAt(new Date(date-1420000));
        CSharpTopics[9].setTitle("Postman returns 415 when trying to do a POST using form-data, but the same request with JSON works fine");
        CSharpTopics[9].setDescription("I'm building a REST API and testing it out using Postman. I have an end-point which works fine when I test it by sending in raw json data, but I want to expand on this endpoint and allow it to take both json data and accept a file, so I wanted to test my current endpoint without any modifications, and see if I would get back the same result when I test my API using form-data instead of JSON, but it always throws a 415 exception.");

        topicDao.saveAll(Arrays.asList(CSharpTopics));
        userDao.saveAll(Arrays.asList(user));
    }

    private void loadHTMLCSSTopic(){
        long date = new Date().getTime();
        Topic[] HTMLCSSTopics = new Topic[10];

        HTMLCSSTopics[0] = new Topic();
        HTMLCSSTopics[0].setUser(user[2]);
        user[2].getTopics().add(HTMLCSSTopics[0]);
        HTMLCSSTopics[0].setType(Type.HTMLCSS);
        HTMLCSSTopics[0].setCreatedAt(new Date(date-60000));
        HTMLCSSTopics[0].setTitle("picture element, responsive images, webp images and safari fallback not working");
        HTMLCSSTopics[0].setDescription("I would like to use webp images, of course safari doesn't support them so I also added the jpg's as fallbacks for different resolutions.\n" +
                "\n" +
                "In chrome everything works fine, in safari it only works if I remove the webp versions from the image tag.\n" +
                "\n" +
                "But I don't want to abandon webp images just because of safari...");

        HTMLCSSTopics[1] = new Topic();
        HTMLCSSTopics[1].setUser(user[0]);
        user[0].getTopics().add(HTMLCSSTopics[1]);
        HTMLCSSTopics[1].setType(Type.HTMLCSS);
        HTMLCSSTopics[1].setCreatedAt(new Date(date-540000));
        HTMLCSSTopics[1].setTitle("Style font in CSS [closed]");
        HTMLCSSTopics[1].setDescription("Can anyone please help me to design text as below. ( Apply background for each and every font and the background should be same as font ).");

        HTMLCSSTopics[2] = new Topic();
        HTMLCSSTopics[2].setUser(user[1]);
        user[1].getTopics().add(HTMLCSSTopics[2]);
        HTMLCSSTopics[2].setType(Type.HTMLCSS);
        HTMLCSSTopics[2].setCreatedAt(new Date(date-2360000));
        HTMLCSSTopics[2].setTitle("How can I add Titles to my Bootstrap video carousel");
        HTMLCSSTopics[2].setDescription("I'm trying to add titles to my bootstrap video carousel, so each video will display a different title at the bottom. I've tried adding a Title tag to the video but this doesn't work. I have also tried using the example on mdbootstrap.com (as you can see below). This does work but stacks all 3 titles from my carousel on top of each other, they don't disappear and reappear depending on which slide is up.");

        HTMLCSSTopics[3] = new Topic();
        HTMLCSSTopics[3].setUser(user[9]);
        user[9].getTopics().add(HTMLCSSTopics[3]);
        HTMLCSSTopics[3].setType(Type.HTMLCSS);
        HTMLCSSTopics[3].setCreatedAt(new Date(date-1230000));
        HTMLCSSTopics[3].setTitle("");
        HTMLCSSTopics[3].setDescription("");

        HTMLCSSTopics[4] = new Topic();
        HTMLCSSTopics[4].setUser(user[3]);
        user[3].getTopics().add(HTMLCSSTopics[4]);
        HTMLCSSTopics[4].setType(Type.HTMLCSS);
        HTMLCSSTopics[4].setCreatedAt(new Date(date-14000));
        HTMLCSSTopics[4].setTitle("How to limit formatting of navigation menu to just the main menu and NOT to the items in the drop-downs. HTML, CSS");
        HTMLCSSTopics[4].setDescription("Hi, if you look at the above screenshot you can see I have added in code to make a small bar transition across when hovering over items in the navigation bar.\n" +
                "\n" +
                "However, this bar also appears when I hover over items in the drop down menu. For example, when hovering over \"Stock 1\" the bar appears and obviously looks awful.\n" +
                "\n" +
                "How can I make it so that this bar only appears on the items in the main menu and NOT in the drop down menu's.");

        HTMLCSSTopics[5] = new Topic();
        HTMLCSSTopics[5].setUser(user[2]);
        user[2].getTopics().add(HTMLCSSTopics[5]);
        HTMLCSSTopics[5].setType(Type.HTMLCSS);
        HTMLCSSTopics[5].setCreatedAt(new Date(date-70000));
        HTMLCSSTopics[5].setTitle("How do you make a navigation bar sit parallel with the logo?");
        HTMLCSSTopics[5].setDescription("I am trying to make my nav bar sit parallel with my logo but I'm having difficulty doing this.\n" +
                "\n" +
                "First of all, when I enter the code for the image, the image afterwards doesn't display at the top of the page.\n" +
                "\n" +
                "Instead, it sits about 40px below the page. I have tried using floats, but have had no luck.\n" +
                "\n" +
                "I have created a negative value of -20px for the logo to sit further at the top of the page but would like to know if that is normal practice in CSS\n" +
                "\n" +
                "I have tried looking at youtube videos but the code they share doesn't seem to work on my project. I'm just wondering whether the image may be a bit too big for the header");

        HTMLCSSTopics[6] = new Topic();
        HTMLCSSTopics[6].setUser(user[7]);
        user[7].getTopics().add(HTMLCSSTopics[6]);
        HTMLCSSTopics[6].setType(Type.HTMLCSS);
        HTMLCSSTopics[6].setCreatedAt(new Date(date-60000));
        HTMLCSSTopics[6].setTitle("Result on Search Box expand field - CSS");
        HTMLCSSTopics[6].setDescription("I have created a live search box but when a user enters some text in the field my search box goes bigger. I need to create similar like a Facebook search result. My code is below.");

        HTMLCSSTopics[7] = new Topic();
        HTMLCSSTopics[7].setUser(user[4]);
        user[4].getTopics().add(HTMLCSSTopics[7]);
        HTMLCSSTopics[7].setType(Type.HTMLCSS);
        HTMLCSSTopics[7].setCreatedAt(new Date(date-12000));
        HTMLCSSTopics[7].setTitle("Alternate Position Circle Background Pattern Using CSS Or Image [closed]");
        HTMLCSSTopics[7].setDescription("Is there any way how to make a background of circle (with a circle inside) in an alternative position in CSS if possible. Or an image pattern.");

        HTMLCSSTopics[8] = new Topic();
        HTMLCSSTopics[8].setUser(user[8]);
        user[8].getTopics().add(HTMLCSSTopics[8]);
        HTMLCSSTopics[8].setType(Type.HTMLCSS);
        HTMLCSSTopics[8].setCreatedAt(new Date(date-3480000));
        HTMLCSSTopics[8].setTitle("I need to make the text in carousel to be responsive");
        HTMLCSSTopics[8].setDescription("I have used bootstrap 4 classes and media queries but the text disappears when I look up the site on phone.` -----any text ------- -----any text ------- ---any text---");

        HTMLCSSTopics[9] = new Topic();
        HTMLCSSTopics[9].setUser(user[5]);
        user[5].getTopics().add(HTMLCSSTopics[9]);
        HTMLCSSTopics[9].setType(Type.HTMLCSS);
        HTMLCSSTopics[9].setCreatedAt(new Date(date-1420000));
        HTMLCSSTopics[9].setTitle("how to create a check box with rectangle border?\n" +
                "Ask Question");
        HTMLCSSTopics[9].setDescription("I have tried using border property but no able to get the design proper. Please help with design a form like one in the image");

        topicDao.saveAll(Arrays.asList(HTMLCSSTopics));
        userDao.saveAll(Arrays.asList(user));
    }

    private void loadCTopic(){
        long date = new Date().getTime();
        Topic[] cTopics = new Topic[10];

        cTopics[0] = new Topic();
        cTopics[0].setUser(user[0]);
        user[0].getTopics().add(cTopics[0]);
        cTopics[0].setType(Type.C);
        cTopics[0].setCreatedAt(new Date(date-60000));
        cTopics[0].setTitle("Why is this function not printing or doing anything at all?");
        cTopics[0].setDescription("I was making a version of the function strstr and I added printf for testing purposes (I don't intend to keep it) and it's not printing or doing anything. I just wanted to check if what I did is right (I'm absolutely new to C).");

        cTopics[1] = new Topic();
        cTopics[1].setUser(user[1]);
        user[1].getTopics().add(cTopics[1]);
        cTopics[1].setType(Type.C);
        cTopics[1].setCreatedAt(new Date(date-540000));
        cTopics[1].setTitle("What does %s.n means in C programming?");
        cTopics[1].setDescription("I'm using fgets to get string input, when I printf using %s the result came out as character d. After I did some research online, %s.n turns out to be working.");

        cTopics[2] = new Topic();
        cTopics[2].setUser(user[5]);
        user[5].getTopics().add(cTopics[2]);
        cTopics[2].setType(Type.C);
        cTopics[2].setCreatedAt(new Date(date-2360000));
        cTopics[2].setTitle("How to stop icc from eliminating function called from inline assembly");
        cTopics[2].setDescription("I'm making an app that needs to run several tasks concurrently. I can't use threads and such because the app should work without any OS (i.e. straight from the bootsector). Using x86 tasks looks like an overkill (both logically and performance-wise). Thus, I decided to implement a task-switching utility myself. I would save processor state, make a call to the task code and then restore the previous state. So I have to make the call from inline assembly");

        cTopics[3] = new Topic();
        cTopics[3].setUser(user[3]);
        user[3].getTopics().add(cTopics[3]);
        cTopics[3].setType(Type.C);
        cTopics[3].setCreatedAt(new Date(date-1230000));
        cTopics[3].setTitle("Sort the contents of the input text file and creating a make qemu out of it in c language");
        cTopics[3].setDescription("I want to do the following:\n" +
                "\n" +
                "develop c program in such a way that in qemu terminal, executable named sort should be created.\n" +
                "\n" +
                "when given \"sort sample.txt\", it should take contents of the sample and sort the text inside it in ascending order.\n" +
                "\n" +
                "create an output file and dump this output.");

        cTopics[4] = new Topic();
        cTopics[4].setUser(user[7]);
        user[7].getTopics().add(cTopics[4]);
        cTopics[4].setType(Type.C);
        cTopics[4].setCreatedAt(new Date(date-14000));
        cTopics[4].setTitle("C - Bitfield library for large bit fields [closed]");
        cTopics[4].setDescription("Is there a good project/library that can be used to deal with large bitfields in C (or C++).\n" +
                "\n" +
                "Here are the requirements\n" +
                "\n" +
                "Should support large number of bits (e.g 10,000)\n" +
                "Initially I want all bits to be 0.\n" +
                "An allocation request will be for a group of bits (e.g. I want to find first occurrence of '000' (i.e 3 consecutive bits).\n" +
                "Note: The group-size could be 100 (100 consecutive free bits).\n" +
                "The implementation (alloc/dealloc) should be thread safe\n" +
                "I have my own implementation but I am looking to see if there is a good library that can be re-used (versus debugging/fixing all the corner conditions in my implementation)");

        cTopics[5] = new Topic();
        cTopics[5].setUser(user[5]);
        user[5].getTopics().add(cTopics[5]);
        cTopics[5].setType(Type.C);
        cTopics[5].setCreatedAt(new Date(date-70000));
        cTopics[5].setTitle("Can I use an if statement for an if statement's term [closed]");
        cTopics[5].setDescription("Can I do that? İ search it, but I didn't get any answer. I wanted to say a code like this:");

        cTopics[6] = new Topic();
        cTopics[6].setUser(user[9]);
        user[9].getTopics().add(cTopics[6]);
        cTopics[6].setType(Type.C);
        cTopics[6].setCreatedAt(new Date(date-60000));
        cTopics[6].setTitle("Is there any way to handle the incompatibility error thrown by Windows?");
        cTopics[6].setDescription("I know that windows has NTVDM.\n" +
                "\n" +
                "I have a DOS emulator, I just want Windows to pass the arguments to a specific batch file I have created whenever the incompatibility issue is raised.\n" +
                "\n" +
                "Can we do something with ftype or the assoc command.\n" +
                "\n" +
                "I tried modifying the exefile=... but nothing really happened");

        cTopics[7] = new Topic();
        cTopics[7].setUser(user[3]);
        user[3].getTopics().add(cTopics[7]);
        cTopics[7].setType(Type.C);
        cTopics[7].setCreatedAt(new Date(date-12000));
        cTopics[7].setTitle("How can I delete the first zero(s) in a string? (Without using atoi) [closed]");
        cTopics[7].setDescription("I was making a script that is calculating the additions between two natural numbers which decimal lengths should be smaller or same with 10000, and printing a result of the sum.\n" +
                "\n" +
                "Of course, there ain't any variable type that can hold a integer which length is 10000 in C.\n" +
                "\n" +
                "So, I made the program by utilizing the simple additions' calculating logic that all we learn in a school when we were young. And also, I just should use strings to get those gigantic numbers.\n" +
                "\n" +
                "But some results were starting with zero. I knew why did the zero appeared there, but I did prefer to have a result that is like \"1234\", not \"01234\". By the way, all other stuffs were perfect.\n" +
                "\n" +
                "I needed a function that gets input as string, and erases a single zero starts with a string if it exists.\n" +
                "\n" +
                "And could you make it instead of me, please? You should probably consider that the strings we will deal with can have such a length that is smaller or same with 10000.");

        cTopics[8] = new Topic();
        cTopics[8].setUser(user[6]);
        user[6].getTopics().add(cTopics[8]);
        cTopics[8].setType(Type.C);
        cTopics[8].setCreatedAt(new Date(date-3480000));
        cTopics[8].setTitle("Compilation error in function declaration in C");
        cTopics[8].setDescription("I am getting errors during the compilation of this C program and is related to the declaration of the function. What is the problem here? When I declare void display(student); it shows a warning but if I change to void display(struct student st) it shows some errors.");

        cTopics[9] = new Topic();
        cTopics[9].setUser(user[7]);
        user[7].getTopics().add(cTopics[9]);
        cTopics[9].setType(Type.C);
        cTopics[9].setCreatedAt(new Date(date-1420000));
        cTopics[9].setTitle("All breakpoints are ignored in VS code C/C++(GDB) debugging in Ubuntu");
        cTopics[9].setDescription("I have all required json files for vscode c debugging. but while debugging all breakpoints are ignored, although output is printed at the end. Along with output some other line is also printed.\n" +
                "\n" +
                "I have written c code for reversing array.\n" +
                "\n" +
                "sampleInput = 1 2 3 4 5 6\n" +
                "\n" +
                "sampleOutput(expected) = 654321");

        topicDao.saveAll(Arrays.asList(cTopics));
        userDao.saveAll(Arrays.asList(user));
    }

    private void loadJavaScriptTopic(){
        long date = new Date().getTime();
        Topic[] JavaScriptTopics = new Topic[10];

        JavaScriptTopics[0] = new Topic();
        JavaScriptTopics[0].setUser(user[7]);
        user[7].getTopics().add(JavaScriptTopics[0]);
        JavaScriptTopics[0].setType(Type.JAVASCRIPT);
        JavaScriptTopics[0].setCreatedAt(new Date(date-60000));
        JavaScriptTopics[0].setTitle("Angular 7 Input Mask for phone number");
        JavaScriptTopics[0].setDescription("I am trying to write code input mask to format the phone number like (123) 456-7890. I almost done.\n" +
                "\n" +
                "I created directive to perform the format.\n" +
                "\n" +
                "but i stuck in paste operation.\n" +
                "\n" +
                "If the user type the phone number in textbox its working fine.\n" +
                "\n" +
                "But if the user copy and paste the 10 digit phone number in textbox the formatting happened only in textbox not reflecting in model binding.\n" +
                "\n" +
                "textbox show -> (123) 456-7890 but in ngModel bind is 1234567890\n" +
                "\n" +
                "Working code in StackBlitz");

        JavaScriptTopics[1] = new Topic();
        JavaScriptTopics[1].setUser(user[6]);
        user[6].getTopics().add(JavaScriptTopics[1]);
        JavaScriptTopics[1].setType(Type.JAVASCRIPT);
        JavaScriptTopics[1].setCreatedAt(new Date(date-540000));
        JavaScriptTopics[1].setTitle("How to parameterize runtime variables in Javascript");
        JavaScriptTopics[1].setDescription("Apologies in advance for the newbie question. I am a newcomer to Javascript development and have inherited a project with which to play and learn.\n" +
                "\n" +
                "I have a class which constructs a Vehicle object. It assigns a dealer code to the vehicle as a String value. Currently this is hardcoded thus:");

        JavaScriptTopics[2] = new Topic();
        JavaScriptTopics[2].setUser(user[0]);
        user[0].getTopics().add(JavaScriptTopics[2]);
        JavaScriptTopics[2].setType(Type.JAVASCRIPT);
        JavaScriptTopics[2].setCreatedAt(new Date(date-2360000));
        JavaScriptTopics[2].setTitle("Pixabay API CORB issue");
        JavaScriptTopics[2].setDescription("I am getting Cross-Origin Read-Blocking (CORB) when trying to access the Pixabay api from within my Javascript (React) app.");

        JavaScriptTopics[3] = new Topic();
        JavaScriptTopics[3].setUser(user[1]);
        user[1].getTopics().add(JavaScriptTopics[3]);
        JavaScriptTopics[3].setType(Type.JAVASCRIPT);
        JavaScriptTopics[3].setCreatedAt(new Date(date-1230000));
        JavaScriptTopics[3].setTitle("Javascript - Change input “checkbox” status for identical inputs across all HTML pages");
        JavaScriptTopics[3].setDescription("I'm new to javascript so go easy! I have created an identical toggle switch on all of my pages for a website. After much fruitless searching, I was wondering if anyone could tell me how to change the status of the other toggle switches across all of the other pages when any one particular switch is used. I've managed to get the switch to do what I want, which is change the css style sheet, but want to take it a step further. Many thanks in advance. Here's my code so far:");

        JavaScriptTopics[4] = new Topic();
        JavaScriptTopics[4].setUser(user[2]);
        user[2].getTopics().add(JavaScriptTopics[4]);
        JavaScriptTopics[4].setType(Type.JAVASCRIPT);
        JavaScriptTopics[4].setCreatedAt(new Date(date-14000));
        JavaScriptTopics[4].setTitle("Can't access cluster_ (Array) in google maps clusterer");
        JavaScriptTopics[4].setDescription("The Problem is that I can't access the clusters. I am calling the clusterMarkers() function, every time a new Marker gets added to the map. In clearMarkers() the old markers were deleted, before the new will be created.\n" +
                "\n" +
                "in showClusterMarkers() I now want the access each individual marker. I can see all clusters in the console but can't access them...");

        JavaScriptTopics[5] = new Topic();
        JavaScriptTopics[5].setUser(user[8]);
        user[8].getTopics().add(JavaScriptTopics[5]);
        JavaScriptTopics[5].setType(Type.JAVASCRIPT);
        JavaScriptTopics[5].setCreatedAt(new Date(date-70000));
        JavaScriptTopics[5].setTitle("How can i add a child to a list of data on firebase that has a unique name?\n");
        JavaScriptTopics[5].setDescription("Here is my firebase database:\n" +
                "\n" +
                "https://imgur.com/a/XmIZafP (not enough points to upload image yet, hence imgur link)\n" +
                "\n" +
                "I am trying to make a todo list on the web using firebase to store it and retrieve it, currently I have 2 methods, 1 creates a todo list with a user and 1 task:");

        JavaScriptTopics[6] = new Topic();
        JavaScriptTopics[6].setUser(user[7]);
        user[7].getTopics().add(JavaScriptTopics[6]);
        JavaScriptTopics[6].setType(Type.JAVASCRIPT);
        JavaScriptTopics[6].setCreatedAt(new Date(date-60000));
        JavaScriptTopics[6].setTitle("RxJS: Unsafe catch usage in effects and epics is forbidden and request nesting");
        JavaScriptTopics[6].setDescription("I have an effect, main idea of it is to make some 'magic' with API, the main problem: first I need to make one request, after it's successful I need to make second request. And everything was ok, until I changed my code to this (to look more readable):");

        JavaScriptTopics[7] = new Topic();
        JavaScriptTopics[7].setUser(user[5]);
        user[5].getTopics().add(JavaScriptTopics[7]);
        JavaScriptTopics[7].setType(Type.JAVASCRIPT);
        JavaScriptTopics[7].setCreatedAt(new Date(date-12000));
        JavaScriptTopics[7].setTitle("How to have a config.js file as an external (it being required during run time and not bundled) in webpack");
        JavaScriptTopics[7].setDescription("I am new to webpack. I am making a react application which will read text from an external config.js file and display it on the UI. I am trying to have a external config.js file which I dont want to be bundled but be required during run time. This is my webpack.config.js:");

        JavaScriptTopics[8] = new Topic();
        JavaScriptTopics[8].setUser(user[4]);
        user[4].getTopics().add(JavaScriptTopics[8]);
        JavaScriptTopics[8].setType(Type.JAVASCRIPT);
        JavaScriptTopics[8].setCreatedAt(new Date(date-3480000));
        JavaScriptTopics[8].setTitle("updating photoURL of a user by accessing from firebase storage");
        JavaScriptTopics[8].setDescription("I am using this javascript code to upload an image to firebase storage then I want to update this image url to user's photoURL (firebase auth)");

        JavaScriptTopics[9] = new Topic();
        JavaScriptTopics[9].setUser(user[3]);
        user[3].getTopics().add(JavaScriptTopics[9]);
        JavaScriptTopics[9].setType(Type.JAVASCRIPT);
        JavaScriptTopics[9].setCreatedAt(new Date(date-1420000));
        JavaScriptTopics[9].setTitle("How to have a config.js file as an external (it being required during run time and not bundled) in webpack");
        JavaScriptTopics[9].setDescription("I am new to webpack. I am making a react application which will read text from an external config.js file and display it on the UI. I am trying to have a external config.js file which I dont want to be bundled but be required during run time. This is my webpack.config.js:");

        topicDao.saveAll(Arrays.asList(JavaScriptTopics));
        userDao.saveAll(Arrays.asList(user));
    }

    private void loadRubyTopic(){
        long date = new Date().getTime();
        Topic[] rubyTopics = new Topic[10];

        rubyTopics[0] = new Topic();
        rubyTopics[0].setUser(user[5]);
        user[5].getTopics().add(rubyTopics[0]);
        rubyTopics[0].setType(Type.RUBY);
        rubyTopics[0].setCreatedAt(new Date(date-60000));
        rubyTopics[0].setTitle("Can't see a variable from a controller on a view - Rails 6");
        rubyTopics[0].setDescription("My name is Carlos, I'm writing and I'm new on Rails 6.\n" +
                "\n" +
                "I'm working on my app trying to show a variable, got from a form, on a view but I can't see it.\n" +
                "\n" +
                "I'm using form_with sending the params(without model) to an URL of a Controller.\n" +
                "\n" +
                "Form on Meteo View (app\\views\\meteo\\form.html.erb):\n" +
                "\n" +
                "Sending the params \":condition_met\" to \"briefing show controller URL\":");

        rubyTopics[1] = new Topic();
        rubyTopics[1].setUser(user[0]);
        user[0].getTopics().add(rubyTopics[1]);
        rubyTopics[1].setType(Type.RUBY);
        rubyTopics[1].setCreatedAt(new Date(date-540000));
        rubyTopics[1].setTitle("How to list all child objects for all parent objects?");
        rubyTopics[1].setDescription("What I have at the moment is pretty standard set of code, where all child objects can be list under only their parent objects.");

        rubyTopics[2] = new Topic();
        rubyTopics[2].setUser(user[1]);
        user[1].getTopics().add(rubyTopics[2]);
        rubyTopics[2].setType(Type.RUBY);
        rubyTopics[2].setCreatedAt(new Date(date-2360000));
        rubyTopics[2].setTitle("Ruby HTTP request always returns 403 response (too many requests). Works in Postman/browser");
        rubyTopics[2].setDescription("I am trying to write a simple function which would easily extract the contact information from a classified listing.");

        rubyTopics[3] = new Topic();
        rubyTopics[3].setUser(user[7]);
        user[7].getTopics().add(rubyTopics[3]);
        rubyTopics[3].setType(Type.RUBY);
        rubyTopics[3].setCreatedAt(new Date(date-1230000));
        rubyTopics[3].setTitle("How could I store a select option in a variable beyond just submittable params to be stored in/used in a helper method in a rails");
        rubyTopics[3].setDescription("Trying to figure out the logic to how I can pull the group_id selected from the join group form and store it into a helper method similar to current_user method. I have to adhere to strict has_many through and am stumped on how I would get the correct group_id to match each post to display with each method or even sort posts by group_id at the moment. Some direction would be greatly appreciated...");

        rubyTopics[4] = new Topic();
        rubyTopics[4].setUser(user[9]);
        user[9].getTopics().add(rubyTopics[4]);
        rubyTopics[4].setType(Type.RUBY);
        rubyTopics[4].setCreatedAt(new Date(date-14000));
        rubyTopics[4].setTitle("Ruby: .each and .collect/.map with conversion methods [closed]");
        rubyTopics[4].setDescription("In my understanding .each takes an array and applies the given block over all items. It doesn't affect the array or creates a new object. While .collect returns the new array. Got it. But then why");

        rubyTopics[5] = new Topic();
        rubyTopics[5].setUser(user[6]);
        user[6].getTopics().add(rubyTopics[5]);
        rubyTopics[5].setType(Type.RUBY);
        rubyTopics[5].setCreatedAt(new Date(date-70000));
        rubyTopics[5].setTitle("How do people find methods or some workings of tools/libraries that are not mentioned in documentation? [closed]");
        rubyTopics[5].setDescription("I often find myself looking at examples of code (like tutorials in Medium) rather than the official documentation because I often find these tutorials/examples containing method that are not mentioned in the documentation or explain things better.\n" +
                "\n" +
                "How do these people find those extra functions and how can I better understand the usage?");

        rubyTopics[6] = new Topic();
        rubyTopics[6].setUser(user[2]);
        user[2].getTopics().add(rubyTopics[6]);
        rubyTopics[6].setType(Type.RUBY);
        rubyTopics[6].setCreatedAt(new Date(date-60000));
        rubyTopics[6].setTitle("How to create a special table in a database that can have all the fields of a subordinate table and only one field of a subordinate table?");
        rubyTopics[6].setDescription("I'm use ruby on rails for create api app. I need to create table Stage, that has one to many relationship with table Task(this table has columns: :status, :stage_id). How can i add stages that will not have tasks. Just a status? db/schema.rb");

        rubyTopics[7] = new Topic();
        rubyTopics[7].setUser(user[5]);
        user[5].getTopics().add(rubyTopics[7]);
        rubyTopics[7].setType(Type.RUBY);
        rubyTopics[7].setCreatedAt(new Date(date-12000));
        rubyTopics[7].setTitle("Rails routing error Started PATCH “/category/8” No route matches [PATCH] “/8”");
        rubyTopics[7].setDescription("i get this error in rails production running on passenger. it work fine in development on my localhost. but on server production i get this error when in want to update category");

        rubyTopics[8] = new Topic();
        rubyTopics[8].setUser(user[1]);
        user[1].getTopics().add(rubyTopics[8]);
        rubyTopics[8].setType(Type.RUBY);
        rubyTopics[8].setCreatedAt(new Date(date-3480000));
        rubyTopics[8].setTitle("MiniTest mocking when new object created inside method");
        rubyTopics[8].setDescription("How do I mock out the WidgetCopyer.new(original_widget, new_widget).copy given the new_widget is created inside the loop?");

        rubyTopics[9] = new Topic();
        rubyTopics[9].setUser(user[7]);
        user[7].getTopics().add(rubyTopics[9]);
        rubyTopics[9].setType(Type.RUBY);
        rubyTopics[9].setCreatedAt(new Date(date-1420000));
        rubyTopics[9].setTitle("Pass Uploaded File path To File.read(path)");
        rubyTopics[9].setDescription("As you can see from the picture, i have a button to upload a csv file. How can i get the uploaded file to file.read(PATH) so the script takes the file in.");

        topicDao.saveAll(Arrays.asList(rubyTopics));
        userDao.saveAll(Arrays.asList(user));
    }

    private void loadSQLTopic(){
        long date = new Date().getTime();
        Topic[] sqlTopics = new Topic[10];

        sqlTopics[0] = new Topic();
        sqlTopics[0].setUser(user[5]);
        user[5].getTopics().add(sqlTopics[0]);
        sqlTopics[0].setType(Type.SQL);
        sqlTopics[0].setCreatedAt(new Date(date-60000));
        sqlTopics[0].setTitle("what statement to use in sql for dynamic pivot table with dates");
        sqlTopics[0].setDescription("I have hardcoded week values (9, 8, 7, 6) into my sql pivot statement where week 9 is current week. Week 8 is 1 week ago, wk 7 is 2 wks ago, etc.\n" +
                "\n" +
                "is there a way to write a statement so that pivot would be dynamic instead of static? so that instead of putting [9], [8], [7], [6] I use something to get current week sales, last week sales, sales 2 weeks ago, etc, etc.");

        sqlTopics[1] = new Topic();
        sqlTopics[1].setUser(user[6]);
        user[6].getTopics().add(sqlTopics[1]);
        sqlTopics[1].setType(Type.SQL);
        sqlTopics[1].setCreatedAt(new Date(date-540000));
        sqlTopics[1].setTitle("How to set a SQL query to choose which table to JOIN depending on source table data?");
        sqlTopics[1].setDescription("I have a table of events. Each event relates to one of four topics; The original data was for only one topic so was a simple 1-to-1 table relationship.\n" +
                "\n" +
                "But the client now wants to extend events to four different topics;");

        sqlTopics[2] = new Topic();
        sqlTopics[2].setUser(user[1]);
        user[1].getTopics().add(sqlTopics[2]);
        sqlTopics[2].setType(Type.SQL);
        sqlTopics[2].setCreatedAt(new Date(date-2360000));
        sqlTopics[2].setTitle("Duplicate records after sorting by a property in a joined table");
        sqlTopics[2].setDescription("I have faced one trouble in my project, therefore I will provide an example using a demo project. Imagine there are two entities: teacher and student.");

        sqlTopics[3] = new Topic();
        sqlTopics[3].setUser(user[8]);
        user[8].getTopics().add(sqlTopics[3]);
        sqlTopics[3].setType(Type.SQL);
        sqlTopics[3].setCreatedAt(new Date(date-1230000));
        sqlTopics[3].setTitle("Mysql is not returning records with NULL value");
        sqlTopics[3].setDescription("I have a column in my table as supplier_id. When I apply the where condition as supplier_id <> 2 then it is not returning the records that have NULL in the supplier_id column.");

        sqlTopics[4] = new Topic();
        sqlTopics[4].setUser(user[6]);
        user[6].getTopics().add(sqlTopics[4]);
        sqlTopics[4].setType(Type.SQL);
        sqlTopics[4].setCreatedAt(new Date(date-14000));
        sqlTopics[4].setTitle("Connection to a Azure SQL DB in many modules needed - how to?");
        sqlTopics[4].setDescription("I have written a Python Tool with an wxPython GUI which has mainly the task to get a lot of user input regarding Customer Data, Product Data and so on and save it to a SQL Database, at the moment locally with a SQLite3 Database for testing an now switching to MS Azure to have anybody work in the same Database.");

        sqlTopics[5] = new Topic();
        sqlTopics[5].setUser(user[3]);
        user[3].getTopics().add(sqlTopics[5]);
        sqlTopics[5].setType(Type.SQL);
        sqlTopics[5].setCreatedAt(new Date(date-70000));
        sqlTopics[5].setTitle("SQL returning multiple max value rows?");
        sqlTopics[5].setDescription("i have a table with prices and want to return the customer who spent the most money on the items. My querie works but in case i have multiple customers with the same amount of money spent(max) it wont show both of them but only one.");

        sqlTopics[6] = new Topic();
        sqlTopics[6].setUser(user[8]);
        user[8].getTopics().add(sqlTopics[6]);
        sqlTopics[6].setType(Type.SQL);
        sqlTopics[6].setCreatedAt(new Date(date-60000));
        sqlTopics[6].setTitle("how to save all special characters in mysql database");
        sqlTopics[6].setDescription("In a database table i have a column name 'Description' but i would like to save any special character in the description columns user input is");

        sqlTopics[7] = new Topic();
        sqlTopics[7].setUser(user[4]);
        user[4].getTopics().add(sqlTopics[7]);
        sqlTopics[7].setType(Type.SQL);
        sqlTopics[7].setCreatedAt(new Date(date-12000));
        sqlTopics[7].setTitle("How to Write alter statement to alter Temp Table with Dynamic name");
        sqlTopics[7].setDescription("I have written Following Query what is my fault please guide me\n" +
                "\n" +
                "set @sqlAlter= 'alter table #RD_Temp'+ @UserNum + ' add '+ @columnname +' DECIMAL(18,2)'\n" +
                "Execute sp_executesql @sqlAlter\n" +
                "\n" +
                "here #RD_Temp'+ @UserNum is the dynamic name for my temp table and @UserNum = 1 which is changed as per user and @columnname is dynamic column\n" +
                "\n" +
                "it shows me following error 'Cannot find the object \"#RD_Temp1\" because it does not exist or you do not have permissions.'");

        sqlTopics[8] = new Topic();
        sqlTopics[8].setUser(user[2]);
        user[2].getTopics().add(sqlTopics[8]);
        sqlTopics[8].setType(Type.SQL);
        sqlTopics[8].setCreatedAt(new Date(date-3480000));
        sqlTopics[8].setTitle("Getting error “near ”(“: syntax error sqlite error”");
        sqlTopics[8].setDescription("I am using sqlite version 3.21.0.\n" +
                "\n" +
                "I am getting error in executing the below query. pd is pandas library. It seems that command is correct.\n" +
                "\n" +
                "This code fetches Gender and their respective count in the Person table using over(partition by Gender) clause.");

        sqlTopics[9] = new Topic();
        sqlTopics[9].setUser(user[1]);
        user[1].getTopics().add(sqlTopics[9]);
        sqlTopics[9].setType(Type.SQL);
        sqlTopics[9].setCreatedAt(new Date(date-1420000));
        sqlTopics[9].setTitle("This query would be too heavy , need to be refactored. how can i do?");
        sqlTopics[9].setDescription("This query would be too heavy, needs to be refactored. How can I do that?");

        topicDao.saveAll(Arrays.asList(sqlTopics));
        userDao.saveAll(Arrays.asList(user));
    }

    private void loadPHPTopic(){
        long date = new Date().getTime();
        Topic[] phpTopic = new Topic[10];

        phpTopic[0] = new Topic();
        phpTopic[0].setUser(user[3]);
        user[3].getTopics().add(phpTopic[0]);
        phpTopic[0].setType(Type.PHP);
        phpTopic[0].setCreatedAt(new Date(date-60000));
        phpTopic[0].setTitle("How to make unique profile pages");
        phpTopic[0].setDescription("My latest project is making a social network like Facebook. But i'm not sure how i make unique pages for like profiles and other stuff... I've searched it up many times but without luck: What should i do to create this?");

        phpTopic[1] = new Topic();
        phpTopic[1].setUser(user[5]);
        user[5].getTopics().add(phpTopic[1]);
        phpTopic[1].setType(Type.PHP);
        phpTopic[1].setCreatedAt(new Date(date-540000));
        phpTopic[1].setTitle("implode an array with “,”");
        phpTopic[1].setDescription("I call the Proxmox API to get the names of the vm in each node. This part of code works well (more complete part of my code at the end of this post) :");

        phpTopic[2] = new Topic();
        phpTopic[2].setUser(user[7]);
        user[7].getTopics().add(phpTopic[2]);
        phpTopic[2].setType(Type.PHP);
        phpTopic[2].setCreatedAt(new Date(date-2360000));
        phpTopic[2].setTitle("Symfony 4 get select option value from twig to Controller not working");
        phpTopic[2].setDescription("I am trying to get the value of my selected option from twig to Controller function, I tried the following but the value returned is NULL and the rest of code depends on this value so nothing is working when trying this. I tried both methods \"$_GET['category']\" and \"$request->query->get('category')\"");

        phpTopic[3] = new Topic();
        phpTopic[3].setUser(user[9]);
        user[9].getTopics().add(phpTopic[3]);
        phpTopic[3].setType(Type.PHP);
        phpTopic[3].setCreatedAt(new Date(date-1230000));
        phpTopic[3].setTitle("magento 2.3 get estimate block selected country and state");
        phpTopic[3].setDescription("In magento 2.3 cart page. when customer didn't start checkout he selects the estimate country and state in block. In case of page reload they become there as previously selected. Magento save selected values in session or database but cant figure out how to access them.");

        phpTopic[4] = new Topic();
        phpTopic[4].setUser(user[2]);
        user[2].getTopics().add(phpTopic[4]);
        phpTopic[4].setType(Type.PHP);
        phpTopic[4].setCreatedAt(new Date(date-14000));
        phpTopic[4].setTitle("WooCommerce REST API php library issue");
        phpTopic[4].setDescription("I want to use the woocommerce rest api to create an infinite scroll using vuejs for my products, that are separated in two categories with subcategories. I'm not able to register a rest route that will rely on the woocommerce php client library, this because I get two errors and I'm not able to fix them. The firs error is about the ssl, I'm testing it on localhost so I have disabled the ssl verification and I've setted the timeout to 30 seconds. Also with this settings I get a timeout error");

        phpTopic[5] = new Topic();
        phpTopic[5].setUser(user[1]);
        user[1].getTopics().add(phpTopic[5]);
        phpTopic[5].setType(Type.PHP);
        phpTopic[5].setCreatedAt(new Date(date-70000));
        phpTopic[5].setTitle("PHP MySql time calculations");
        phpTopic[5].setDescription("I want to calculate how much time the worker has left since break start.\n" +
                "\n" +
                "By the click, PHP insert into history table a record with timestamp (break_start), after second click, PHP update record with timestamp (break_end). Now i can calculate time difference using this code:");

        phpTopic[6] = new Topic();
        phpTopic[6].setUser(user[8]);
        user[8].getTopics().add(phpTopic[6]);
        phpTopic[6].setType(Type.PHP);
        phpTopic[6].setCreatedAt(new Date(date-60000));
        phpTopic[6].setTitle("How to get all items list on Net-suite using PHP toolkit?");
        phpTopic[6].setDescription("I am using PHP toolkit from Net-suite. but i cant get way to fetch all items from netsuite so can you please help me for this. Thank you");

        phpTopic[7] = new Topic();
        phpTopic[7].setUser(user[3]);
        user[3].getTopics().add(phpTopic[7]);
        phpTopic[7].setType(Type.PHP);
        phpTopic[7].setCreatedAt(new Date(date-12000));
        phpTopic[7].setTitle("Cpanel Soap Webservice");
        phpTopic[7].setDescription("Im using php soap to connect to a xml webservice the code works fine via local connection , but after uploading it to the cpanel webserver i get failed to load external entity could it be a configuration issue or a firewall issue at the host webservice?");

        phpTopic[8] = new Topic();
        phpTopic[8].setUser(user[6]);
        user[6].getTopics().add(phpTopic[8]);
        phpTopic[8].setType(Type.PHP);
        phpTopic[8].setCreatedAt(new Date(date-3480000));
        phpTopic[8].setTitle("Find the value of a key in an unknown multilevel array in PHP");
        phpTopic[8].setDescription("I have a use defined json string inside a database.\n" +
                "\n" +
                "The JSON string has lots of levels. I know my user will define a kay called \"basevalue\" and place it somewhere in the json.\n" +
                "\n" +
                "The problem is, I don't know ahead of time where in the JSON it will be placed, and every use is likely to place is in different places in the array, perhaps at different levels.");

        phpTopic[9] = new Topic();
        phpTopic[9].setUser(user[5]);
        user[5].getTopics().add(phpTopic[9]);
        phpTopic[9].setType(Type.PHP);
        phpTopic[9].setCreatedAt(new Date(date-1420000));
        phpTopic[9].setTitle("How can i selected all the data in one click");
        phpTopic[9].setDescription("I have to issue to fix as you can see in this picture I want to change it as all student who in ICT A will be selected in but as you can see all of the students in ICT A is showing (I just rename it) this is the source code please help me to select all the student in ICT A in just one click");

        topicDao.saveAll(Arrays.asList(phpTopic));
        userDao.saveAll(Arrays.asList(user));
    }

    private void loadSwiftTopic(){
        long date = new Date().getTime();
        Topic[] swiftTopics = new Topic[10];

        swiftTopics[0] = new Topic();
        swiftTopics[0].setUser(user[1]);
        user[1].getTopics().add(swiftTopics[0]);
        swiftTopics[0].setType(Type.SWIFT);
        swiftTopics[0].setCreatedAt(new Date(date-60000));
        swiftTopics[0].setTitle("UITapGestureRecognizer for UILabel inside of a StackView");
        swiftTopics[0].setDescription("I have a Stack View with two labels, one of which once tapped, suppose to lead to another view.\n" +
                "\n" +
                "Here the code of my UIView subclass where labels and a StackView are setup:");

        swiftTopics[1] = new Topic();
        swiftTopics[1].setUser(user[0]);
        user[0].getTopics().add(swiftTopics[1]);
        swiftTopics[1].setType(Type.SWIFT);
        swiftTopics[1].setCreatedAt(new Date(date-540000));
        swiftTopics[1].setTitle("UITapGestureRecognizer for UILabel inside of a StackView");
        swiftTopics[1].setDescription("I have a Stack View with two labels, one of which once tapped, suppose to lead to another view.\n" +
                "\n" +
                "Here the code of my UIView subclass where labels and a StackView are setup:");

        swiftTopics[2] = new Topic();
        swiftTopics[2].setUser(user[2]);
        user[2].getTopics().add(swiftTopics[2]);
        swiftTopics[2].setType(Type.SWIFT);
        swiftTopics[2].setCreatedAt(new Date(date-2360000));
        swiftTopics[2].setTitle("update variable from ViewController to a view");
        swiftTopics[2].setDescription("I have a viewController A and a popView B. 1) Click a button from A to pop View B. 2) Click a button from B to a controller C. 3) Click confirm button from controller C to return a value.\n" +
                "\n" +
                "ViewController A has the delegate function that manage 3).\n" +
                "\n" +
                "I want to change the button title in 2) after doing action 3).\n" +
                "\n" +
                "The code I have now is below:");

        swiftTopics[3] = new Topic();
        swiftTopics[3].setUser(user[7]);
        user[7].getTopics().add(swiftTopics[3]);
        swiftTopics[3].setType(Type.SWIFT);
        swiftTopics[3].setCreatedAt(new Date(date-1230000));
        swiftTopics[3].setTitle("How to import Swift Package in Playground?");
        swiftTopics[3].setDescription("Can i import Swift Package to my Playground that is in my workspace? I've already read the answer, but i think it's outdated, because now we can use Swift Packages in iOS projects.");

        swiftTopics[4] = new Topic();
        swiftTopics[4].setUser(user[9]);
        user[9].getTopics().add(swiftTopics[4]);
        swiftTopics[4].setType(Type.SWIFT);
        swiftTopics[4].setCreatedAt(new Date(date-14000));
        swiftTopics[4].setTitle("Why does removeDeliveredNotifications(withIdentifiers: identifiers) remove notifications with identifiers that are not specified?");
        swiftTopics[4].setDescription("I'm building a notification based swift app for iOS. On receiving a notification you can react to the notification with UNNotificationAction. However, when you perform a certain action from within the application itself e.g. push of a button, the delivered notification becomes obsolete and has to be removed.\n" +
                "\n" +
                "Now comes the problem. When the app calls removeDeliveredNotifications(withIdentifiers: identifiers) where the variable identifiers is an array with just one string, on some occasions it removes all of the displayed notifications.\n" +
                "\n" +
                "How can this possibly happen when each individual notification holds just one unique identifier?\n" +
                "\n" +
                "Thanks in advance.\n" +
                "\n" +
                "If you need more information I will be happy to assist you with that");

        swiftTopics[5] = new Topic();
        swiftTopics[5].setUser(user[5]);
        user[5].getTopics().add(swiftTopics[5]);
        swiftTopics[5].setType(Type.SWIFT);
        swiftTopics[5].setCreatedAt(new Date(date-70000));
        swiftTopics[5].setTitle("Swift: conditional modifier for .font of text()");
        swiftTopics[5].setDescription("I am new with Swift an still learning. I tried to format a text based on a boolean variable. It works well with font size and style. But it won't work with styles like .bold() or .italic. Any idea how to do this? I tried ViewModifier too, but there is the same problem.");

        swiftTopics[6] = new Topic();
        swiftTopics[6].setUser(user[3]);
        user[3].getTopics().add(swiftTopics[6]);
        swiftTopics[6].setType(Type.SWIFT);
        swiftTopics[6].setCreatedAt(new Date(date-60000));
        swiftTopics[6].setTitle("/usr/bin/codesign failed with exit code 1");
        swiftTopics[6].setDescription("I am attempting to deploy my first development iPhone app, and am running into some problems. I have successfully went though the online Provisioning Assistant, but now I am stuck. No matter what I do, I always get the following error.");

        swiftTopics[7] = new Topic();
        swiftTopics[7].setUser(user[8]);
        user[8].getTopics().add(swiftTopics[7]);
        swiftTopics[7].setType(Type.SWIFT);
        swiftTopics[7].setCreatedAt(new Date(date-12000));
        swiftTopics[7].setTitle("Xcode Code Coverage not updating even test cases are passed");
        swiftTopics[7].setDescription("I have added many tests and that are passing. But the issue is with code coverage. It always shows 0% Coverage. I have enabled it in the schema to Gather Coverage for all targets. I checked settings with another project and most likely they are same but in another project I am able to see the Coverage. I don't know what might be the reason.\n" +
                "\n" +
                "In my project I have multiple configurations dev, test, prod... Is there any thing specific needed to be done to enable coverage?\n" +
                "\n" +
                "Project Link\n" +
                "\n" +
                "I have created a sample project to check the code coverage is working or not. In the sample project it works shows me the percent and files covered.");

        swiftTopics[8] = new Topic();
        swiftTopics[8].setUser(user[4]);
        user[4].getTopics().add(swiftTopics[8]);
        swiftTopics[8].setType(Type.SWIFT);
        swiftTopics[8].setCreatedAt(new Date(date-3480000));
        swiftTopics[8].setTitle("ios Charts change grid lines color");
        swiftTopics[8].setDescription("I have made a small application measuring Heart Rate from iWatch. The chart is working as intended showing current heart rate, but I have several issues with the design. Below you can find the image of my chart and its setting in swift.");

        swiftTopics[9] = new Topic();
        swiftTopics[9].setUser(user[3]);
        user[3].getTopics().add(swiftTopics[9]);
        swiftTopics[9].setType(Type.SWIFT);
        swiftTopics[9].setCreatedAt(new Date(date-1420000));
        swiftTopics[9].setTitle("ios Charts change grid lines color");
        swiftTopics[9].setDescription("I have made a small application measuring Heart Rate from iWatch. The chart is working as intended showing current heart rate, but I have several issues with the design. Below you can find the image of my chart and its setting in swift.");

        topicDao.saveAll(Arrays.asList(swiftTopics));
        userDao.saveAll(Arrays.asList(user));
    }

    private void loadOtherTopic(){
        long date = new Date().getTime();
        Topic[] otherTopics = new Topic[10];

        otherTopics[0] = new Topic();
        otherTopics[0].setUser(user[0]);
        user[0].getTopics().add(otherTopics[0]);
        otherTopics[0].setType(Type.OTHER);
        otherTopics[0].setCreatedAt(new Date(date-60000));
        otherTopics[0].setTitle("other");
        otherTopics[0].setDescription("other");

        otherTopics[1] = new Topic();
        otherTopics[1].setUser(user[1]);
        user[1].getTopics().add(otherTopics[1]);
        otherTopics[1].setType(Type.OTHER);
        otherTopics[1].setCreatedAt(new Date(date-540000));
        otherTopics[1].setTitle("other");
        otherTopics[1].setDescription("other");

        otherTopics[2] = new Topic();
        otherTopics[2].setUser(user[2]);
        user[2].getTopics().add(otherTopics[2]);
        otherTopics[2].setType(Type.OTHER);
        otherTopics[2].setCreatedAt(new Date(date-2360000));
        otherTopics[2].setTitle("other");
        otherTopics[2].setDescription("other");

        otherTopics[3] = new Topic();
        otherTopics[3].setUser(user[3]);
        user[3].getTopics().add(otherTopics[3]);
        otherTopics[3].setType(Type.OTHER);
        otherTopics[3].setCreatedAt(new Date(date-1230000));
        otherTopics[3].setTitle("other");
        otherTopics[3].setDescription("other");

        otherTopics[4] = new Topic();
        otherTopics[4].setUser(user[4]);
        user[4].getTopics().add(otherTopics[4]);
        otherTopics[4].setType(Type.OTHER);
        otherTopics[4].setCreatedAt(new Date(date-14000));
        otherTopics[4].setTitle("other");
        otherTopics[4].setDescription("other");

        otherTopics[5] = new Topic();
        otherTopics[5].setUser(user[5]);
        user[5].getTopics().add(otherTopics[5]);
        otherTopics[5].setType(Type.OTHER);
        otherTopics[5].setCreatedAt(new Date(date-70000));
        otherTopics[5].setTitle("other");
        otherTopics[5].setDescription("other");

        otherTopics[6] = new Topic();
        otherTopics[6].setUser(user[6]);
        user[6].getTopics().add(otherTopics[6]);
        otherTopics[6].setType(Type.OTHER);
        otherTopics[6].setCreatedAt(new Date(date-60000));
        otherTopics[6].setTitle("other");
        otherTopics[6].setDescription("other");

        otherTopics[7] = new Topic();
        otherTopics[7].setUser(user[7]);
        user[7].getTopics().add(otherTopics[7]);
        otherTopics[7].setType(Type.OTHER);
        otherTopics[7].setCreatedAt(new Date(date-12000));
        otherTopics[7].setTitle("other");
        otherTopics[7].setDescription("other");

        otherTopics[8] = new Topic();
        otherTopics[8].setUser(user[8]);
        user[8].getTopics().add(otherTopics[8]);
        otherTopics[8].setType(Type.OTHER);
        otherTopics[8].setCreatedAt(new Date(date-3480000));
        otherTopics[8].setTitle("other");
        otherTopics[8].setDescription("other");

        otherTopics[9] = new Topic();
        otherTopics[9].setUser(user[9]);
        user[9].getTopics().add(otherTopics[9]);
        otherTopics[9].setType(Type.OTHER);
        otherTopics[9].setCreatedAt(new Date(date-1420000));
        otherTopics[9].setTitle("other");
        otherTopics[9].setDescription("other");

        topicDao.saveAll(Arrays.asList(otherTopics));
        userDao.saveAll(Arrays.asList(user));
    }
}
