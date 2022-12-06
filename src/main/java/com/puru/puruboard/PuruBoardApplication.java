package com.puru.puruboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PuruBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuruBoardApplication.class, args);
	}
}
