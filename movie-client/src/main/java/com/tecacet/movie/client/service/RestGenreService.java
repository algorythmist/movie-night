package com.tecacet.movie.client.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.tecacet.movie.client.model.Genre;
import com.tecacet.movie.client.model.GenreList;

public class RestGenreService implements GenreService {

	private static final String LIST_URL = "http://localhost:8080/genres/list";
	private static final String CREATE_URL = "http://localhost:8080/genres/";
	private static final String BY_ID_URL = "http://localhost:8080/genres/{id}";
	private static final String  BY_NAME  = "http://localhost:8080/genres/byname/{name}";

	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<Genre> getAllGenres() {
		return restTemplate.getForObject(LIST_URL, GenreList.class);
	}

	@Override
	public Genre create(String name) {
		return restTemplate.postForObject(CREATE_URL, name, Genre.class);
	}

	@Override
	public Genre findGenreById(long id) {
		return restTemplate.getForObject(BY_ID_URL, Genre.class, id);
	}

	@Override
	public Genre findGenreByName(String name)  {
		return restTemplate.getForObject(BY_NAME, Genre.class, "name");
	}

	@Override
	public void delete(long id) {
		restTemplate.delete(BY_ID_URL, id);
	}
}
