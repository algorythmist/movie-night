package com.tecacet.movie.client;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.tecacet.movie.client.model.Genre;
import com.tecacet.movie.client.model.GenreList;

public class GenreClient {

	private static final String LIST_URL = "http://localhost:8080/genres/list";
	private static final String CREATE_URL = "http://localhost:8080/genres/";
	private static final String BY_ID_URL = "http://localhost:8080/genres/{id}";

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		List<Genre> allGenres = restTemplate.getForObject(LIST_URL, GenreList.class);
		System.out.println(allGenres);

		Genre genre = restTemplate.postForObject(CREATE_URL, "Test", Genre.class);
		System.out.println(genre);
		
		genre = restTemplate.getForObject(BY_ID_URL, Genre.class, genre.getId());
		System.out.println(genre);
		
		
		restTemplate.delete(BY_ID_URL, genre.getId());

	}
}
