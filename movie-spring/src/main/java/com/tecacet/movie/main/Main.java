package com.tecacet.movie.main;

import com.tecacet.movie.config.ApplicationConfiguration;
import com.tecacet.movie.domain.Director;
import com.tecacet.movie.service.DirectorRatingService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        DirectorRatingService ratingService = context.getBean(DirectorRatingService.class);
        List<? extends Director> directors = ratingService.findTopDirectors(10);
        directors.forEach(System.err::println);
    }
}
