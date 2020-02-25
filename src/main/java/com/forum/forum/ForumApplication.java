package com.forum.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForumApplication {


	//TODO thymeleaf th:action miss request param and path variable
	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
