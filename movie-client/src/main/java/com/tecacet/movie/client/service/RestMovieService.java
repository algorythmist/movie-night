package com.tecacet.movie.client.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.tecacet.movie.client.model.Movie;
import com.tecacet.movie.client.model.MovieList;

public class RestMovieService implements MovieService {

	private static final String LIST_URL = "http://localhost:8080/movies/list";
	private static final String CREATE_URL = "http://localhost:8080/movies/";
	private static final String BY_ID_URL = "http://localhost:8080/movies/{id}";

	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<Movie> getAllMovies() {
		return restTemplate.getForObject(LIST_URL, MovieList.class);
	}

	@Override
	public Movie create(Movie movie) {
		return restTemplate.postForObject(CREATE_URL, movie, Movie.class);
	}

	@Override
	public Movie findMovieById(long id) {
		return restTemplate.getForObject(BY_ID_URL, Movie.class, id);
	}

	@Override
	public void delete(long id) {
		restTemplate.delete(BY_ID_URL, id);
	}
}
