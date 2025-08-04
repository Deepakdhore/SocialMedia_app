package com.socialmedia.socialmediaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SocialMediaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaAppApplication.class, args);
	}

}
