package com.amardeep.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.amardeep.mail")
public class SimpleMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleMailApplication.class, args);
	}
}
