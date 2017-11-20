package com.tecacet.movie.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;

import com.tecacet.movie.jpa.config.PersistenceConfiguration;

@SpringBootApplication
@ImportAutoConfiguration(classes = PersistenceConfiguration.class)
@EnableAspectJAutoProxy
@EnableMBeanExport
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
