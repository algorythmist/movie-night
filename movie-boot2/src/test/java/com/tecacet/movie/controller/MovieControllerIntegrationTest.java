package com.tecacet.movie.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tecacet.movie.Application;
import com.tecacet.movie.entity.EntityMovie;
import com.tecacet.movie.repository.MovieRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
public class MovieControllerIntegrationTest {

    private static final String CREATE_URL = "/movies/";
    private static final String BY_ID_URL = "/movies/{id}";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MovieRepository movieRepository;

    @AfterEach
    public void cleanUp() {
        movieRepository.deleteAll();
    }


    @Test
    public void crud() {
        //create

        EntityMovie movie = new EntityMovie("Farewell");
        movie.setDuration(23);
        movie.setReleaseDate(LocalDate.of(2017, 1, 1));
        movie = restTemplate.postForObject(CREATE_URL, movie, EntityMovie.class);
        assertTrue(movie.getId() > 0);
        assertNull(movie.getPlot());

        //add plot to a movie
        movie.setPlot("People saying goodby");
        Map<String, String> params = new HashMap<>();
        params.put("id", Long.toString(movie.getId()));
        restTemplate.put(BY_ID_URL, movie, params);


        EntityMovie byId = restTemplate.getForObject(BY_ID_URL, EntityMovie.class, params);
        assertEquals("People saying goodby", byId.getPlot());

    }

}
