package com.tecacet.movie.client.service;

import com.tecacet.movie.client.model.Movie;
import com.tecacet.movie.client.model.MovieList;

import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestMovieService implements MovieService {

    private static final String LIST_URL = "/movies/list";
    private static final String CREATE_URL = "/movies/";
    private static final String BY_ID_URL = "/movies/{id}";

    private final RestTemplate restTemplate = new RestTemplate();

    private final String baseUrl;

    public RestMovieService(String baseUrl) {
        super();
        this.baseUrl = baseUrl;
    }

    @Override
    public List<Movie> getAllMovies() {
        return restTemplate.getForObject(baseUrl + LIST_URL, MovieList.class);
    }

    @Override
    public Movie create(Movie movie) {
        return restTemplate.postForObject(baseUrl + CREATE_URL, movie, Movie.class);
    }

    @Override
    public Movie findMovieById(long id) {
        return restTemplate.getForObject(baseUrl + BY_ID_URL, Movie.class, id);
    }

    @Override
    public void delete(long id) {
        restTemplate.delete(baseUrl + BY_ID_URL, id);
    }
}
