package com.tecacet.movie.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tecacet.movie.jpa.config.PersistenceConfiguration;

@SpringBootApplication
@ImportAutoConfiguration(classes = PersistenceConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
