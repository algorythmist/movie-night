package com.tecacet.movie.client.service;

import com.tecacet.movie.client.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    Movie create(Movie movie);

    Movie findMovieById(long id);

    void delete(long id);

}