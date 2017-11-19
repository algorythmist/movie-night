package com.tecacet.movie.client;

import org.springframework.web.client.RestTemplate;

import com.tecacet.movie.client.model.Movies;

public class Main {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://localhost:8080/movies/list";
//		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
//		System.out.println(response.getBody());
		
		Movies movies = restTemplate.getForObject(resourceUrl, Movies.class);
		System.out.println(movies);
		
//		String resourceUrl = "http://localhost:8080/movies/{id}";		
//		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class, 1L);
//		System.out.println(response.getBody());
//		
//		Movie movie = restTemplate.getForObject(resourceUrl, Movie.class, 1L);
//		System.out.println(movie);
	}
}
