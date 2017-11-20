package com.tecacet.movie.boot.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.domain.Movie;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MovieControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getAllMovies() {
		List<? extends Movie> movies = restTemplate.getForObject("/movies/list", List.class);
		//TODO assertEquals(4609, movies.size());
	}

}
