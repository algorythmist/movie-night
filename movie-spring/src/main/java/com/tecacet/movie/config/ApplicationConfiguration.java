package com.tecacet.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.tecacet.movie.parser.MovieParser;

@Configuration
@ComponentScan(basePackages = "com.tecacet.movie")
public class ApplicationConfiguration {

	@Bean
	public MovieParser movieParser() {
		return new MovieParser();
	}
	
}
