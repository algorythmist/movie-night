package com.tecacet.movie.client.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.web.client.RestTemplate;

import com.tecacet.movie.client.model.Genre;
import com.tecacet.movie.client.model.GenreList;

public class RestGenreService implements GenreService {

	private final Logger logger = Logger.getLogger("genreService");

	private static final String LIST_URL = "/genres/list";
	private static final String CREATE_URL = "/genres/";
	private static final String BY_ID_URL = "/genres/{id}";
	private static final String BY_NAME = "/genres/byname/{name}";

	private final RestTemplate restTemplate = new RestTemplate();

	private final String baseUrl;

	public RestGenreService(String baseUrl) {
		super();
		this.baseUrl = baseUrl;
	}

	@Override
	public Genre create(String name) {
		return restTemplate.postForObject(baseUrl + CREATE_URL, name, Genre.class);
	}

	@Override
	public List<Genre> getAllGenres() {
		String url = baseUrl + LIST_URL;
		logger.info("getAllGenres: " + url);
		return restTemplate.getForObject(url, GenreList.class);
	}

	@Override
	public Genre findGenreById(long id) {
		String url = baseUrl + BY_ID_URL;
		logger.info("findGenreById: " + url);
		return restTemplate.getForObject(url, Genre.class, id);
	}

	@Override
	public Genre findGenreByName(String name) {
		String url = baseUrl + BY_NAME;
		logger.info("findGenreByName: " + url);
		return restTemplate.getForObject(url, Genre.class, "name");
	}

	@Override
	public void delete(long id) {
		restTemplate.delete(BY_ID_URL, id);
	}
}
