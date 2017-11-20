package com.tecacet.movie.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.tecacet.movie.jpa.config.PersistenceConfiguration;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.tecacet.movie.rest" })
@Import(PersistenceConfiguration.class)
public class RestConfiguration {

}
