package com.tecacet.movie.boot;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.domain.Person;
import com.tecacet.movie.parser.MovieParser;
import com.tecacet.movie.service.MovieService;
import com.tecacet.movie.service.memory.InMemoryMovieService;

@Service
public class FileMovieService implements MovieService {

	private final MovieParser movieParser = new MovieParser();
	private final MovieService delegate;

	public FileMovieService() throws IOException {
		this.delegate = new InMemoryMovieService(movieParser.parse("moviedata.json"));
	}

	public List<? extends Movie> getAllMovies() {
		return delegate.getAllMovies();
	}

	public List<? extends Person> getAllActors() {
		return delegate.getAllActors();
	}

	public List<? extends Person> getAllDirectors() {
		return delegate.getAllDirectors();
	}

	public List<? extends Genre> getAllGenres() {
		return delegate.getAllGenres();
	}

	public List<? extends Movie> findByTitle(String name) {
		return delegate.findByTitle(name);
	}

	public List<? extends Movie> findMoviesWithActor(String actorName) {
		return delegate.findMoviesWithActor(actorName);
	}

	public List<? extends Movie> findMoviesWithDirector(String directorName) {
		return delegate.findMoviesWithDirector(directorName);
	}

	public List<? extends Movie> findMoviesInGenre(String genreName) {
		return delegate.findMoviesInGenre(genreName);
	}

	

}
