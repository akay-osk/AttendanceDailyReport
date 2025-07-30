package com.example.demo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordHashGenerator {
	
	@Bean
	public CommandLineRunner run() {
		return args -> {
			String rawPassword = "pw001";
			String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
			System.out.println("Hashed password: " + encodedPassword);
		};
	}

}
