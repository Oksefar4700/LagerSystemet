package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Lagersystem1Application {

	public static void main(String[] args) {
		SpringApplication.run(Lagersystem1Application.class, args);
	}

}
