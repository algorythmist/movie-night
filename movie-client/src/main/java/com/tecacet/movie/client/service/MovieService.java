package com.tecacet.movie.client.service;

import java.util.List;

import com.tecacet.movie.client.model.Movie;

public interface MovieService {

	List<Movie> getAllMovies();

	Movie create(Movie movie);

	Movie findMovieById(long id);

	void delete(long id);

}