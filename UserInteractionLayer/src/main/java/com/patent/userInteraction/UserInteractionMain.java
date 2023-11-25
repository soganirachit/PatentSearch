package com.patent.userInteraction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.patent.userInteraction" })
public class UserInteractionMain {

	public static void main(String[] args) {

		SpringApplication.run(UserInteractionMain.class, args);

	}

}
