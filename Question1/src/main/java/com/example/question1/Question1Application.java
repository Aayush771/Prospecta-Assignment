package com.example.question1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Question1Application {

	public static void main(String[] args) {
		SpringApplication.run(Question1Application.class, args);
	}
	@Bean
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}
}
