package com.tecacet.movie.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tecacet.movie.Application;
import com.tecacet.movie.entity.EntityGenre;
import com.tecacet.movie.repository.GenreRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
public class GenreControllerIntegrationTest {

    private static final String LIST_URL = "/genres/list";
    private static final String CREATE_URL = "/genres/";
    private static final String BY_ID_URL = "/genres/{id}";
    private static final String BY_NAME_URL = "/genres/byname/{name}";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GenreRepository genreRepository;

    @AfterEach
    public void cleanUp() {
        genreRepository.deleteAll();
    }

    @Test
    public void crud() {
        //create
        EntityGenre genre = restTemplate.postForObject(CREATE_URL, "Test1", EntityGenre.class);
        assertEquals("Test1", genre.getName());

        //find by name
        Map<String, String> params = new HashMap<>();
        params.put("name", "test1");
        EntityGenre found = restTemplate.getForObject(BY_NAME_URL, EntityGenre.class, params);
        assertEquals("Test1", found.getName());

        //create another one
        restTemplate.postForObject(CREATE_URL, "Test2", EntityGenre.class);

        List<EntityGenre> genres = restTemplate.getForObject(LIST_URL, List.class);
        assertEquals(2, genres.size());

        params = new HashMap<>();
        params.put("id", Long.toString(found.getId()));
        EntityGenre byId = restTemplate.getForObject(BY_ID_URL, EntityGenre.class, params);
        assertEquals("Test1", byId.getName());


        restTemplate.delete(BY_ID_URL, genre.getId());

        genres = restTemplate.getForObject(LIST_URL, List.class);
        assertEquals(1, genres.size());
    }

    @Test
    public void missingResource() {
        Map<String, String> params = new HashMap<>();
        params.put("name", "test2");
        ResponseEntity<EntityGenre> response = restTemplate.getForEntity(BY_NAME_URL, EntityGenre.class, params);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
