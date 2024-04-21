package com.example.quora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuoraApplication.class, args);
	}

}
