package com.tecacet.movie.boot.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.jpa.model.EntityMovie;
import com.tecacet.movie.jpa.repository.MovieRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MovieControllerIntegrationTest {

	private static final String CREATE_URL = "/movies/";
	private static final String BY_ID_URL = "/movies/{id}";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private MovieRepository movieRepository;

	@After
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
