package com.tecacet.movie.service;

import java.util.List;

import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.domain.Person;

public interface MovieService {

	List<? extends Movie> getAllMovies();

	List<? extends Person> getAllActors();

	List<? extends Person> getAllDirectors();

	List<? extends Genre> getAllGenres();
	
	List<? extends Movie> findByTitle(String name);
	
	List<? extends Movie> findMoviesWithActor(String actorName);

	List<? extends Movie> findMoviesWithDirector(String directorName);
	
	List<? extends Movie> findMoviesInGenre(String genreName);
}
