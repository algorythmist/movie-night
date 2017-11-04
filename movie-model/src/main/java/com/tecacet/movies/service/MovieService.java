package com.tecacet.movies.service;

import java.util.List;

import com.tecacet.movies.domain.Genre;
import com.tecacet.movies.domain.Movie;
import com.tecacet.movies.domain.Person;

public interface MovieService {

	List<Movie> getAllMovies();

	List<Person> getAllActors();

	List<Person> getAllDirectors();

	List<Genre> getAllGenres();
	
	List<Movie> findMoviesWithActor(String actorName);

	List<Movie> findMoviesWithDirector(String directorName);
	
	List<Movie> findMoviesInGenre(String genreName);
}
