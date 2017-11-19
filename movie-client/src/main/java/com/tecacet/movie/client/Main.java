package com.tecacet.movie.client;

import org.springframework.web.client.RestTemplate;

import com.tecacet.movie.client.model.Movie;

public class Main {
	
	private static final String LIST_URL = "http://localhost:8080/movies/list";
	private static String GET_MOVIE_BY_ID = "http://localhost:8080/movies/{id}";		
	
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		
			
//		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
//		System.out.println(response.getBody());
		
//		Movies movies = restTemplate.getForObject(LIST_URL, Movies.class);
//		System.out.println(movies);
		
//		
//		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class, 1L);
//		System.out.println(response.getBody());
//		
		Movie movie = restTemplate.getForObject(GET_MOVIE_BY_ID, Movie.class, 4614L);
		System.out.println(movie);
	}
}
