package com.tecacet.movie.client;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tecacet.movie.client.model.Movie;

public class Main {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://localhost:8080/movies/list";
//		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
//		System.out.println(response.getBody());
		
		List<Movie> movies = restTemplate.getForObject(resourceUrl, List.class);
		System.out.println(movies);
	}
}
