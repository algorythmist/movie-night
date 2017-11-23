package com.tecacet.movie.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.tecacet.movie.jpa.config.PersistenceConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ImportAutoConfiguration(classes = PersistenceConfiguration.class)
@EnableAspectJAutoProxy
@EnableMBeanExport
@EnableSwagger2
@EnableScheduling
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
