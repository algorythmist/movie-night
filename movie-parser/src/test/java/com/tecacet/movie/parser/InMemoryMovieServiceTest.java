package com.tecacet.movie.parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tecacet.movies.domain.Genre;
import com.tecacet.movies.domain.Movie;
import com.tecacet.movies.service.MovieService;

public class InMemoryMovieServiceTest {

	private static final MovieParser movieParser = new MovieParser();
	private static MovieService movieService;

	@BeforeClass
	public static void setUp() throws IOException {
		List<JsonMovie> movies = movieParser.parse("moviedata.json");
		movieService = new InMemoryMovieService(movies);
	}

	@Test
	public void getAllMovies() {
		List<Movie> movies = movieService.getAllMovies();
		// TODO assertEquals(4609, movies.size());
	}

	@Test
	public void getAllGenres() {
		List<Genre> genres = movieService.getAllGenres();
		assertEquals(24, genres.size());
		//TODO genres.forEach(g -> System.out.println(g));
	}

	@Test
	public void findMoviesWithActor() throws IOException {
		List<Movie> actorMovies = movieService.findMoviesWithActor("Laurence Olivier");
		System.out.println(actorMovies);
	}

	// List<Person> getAllActors();
	//
	// List<Person> getAllDirectors();
	
	// List<Movie> findMoviesWithActor(String actorName);
	//
	// List<Movie> findMoviesWithDirector(String directorName);
	//
	// List<Movie> findMoviesInGenre(String genreName);

}
