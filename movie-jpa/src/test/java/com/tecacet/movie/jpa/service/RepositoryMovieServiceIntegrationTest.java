package com.tecacet.movie.jpa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import jakarta.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.domain.Person;
import com.tecacet.movie.jpa.config.PersistenceConfiguration;
import com.tecacet.movie.service.MovieService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersistenceConfiguration.class })
@Transactional
public class RepositoryMovieServiceIntegrationTest {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private RepositoryMovieService repositoryMovieService;
	
	@Autowired
	private DatabasePopulator databasePopulator;
	
	private AtomicBoolean loaded = new AtomicBoolean(false);
	
	@Before
	public void setUpDatabase() throws IOException {
		if (!loaded.getAndSet(true)) {
			databasePopulator.loadMovies();
		}
	}
	
	@Test
	public void getAllMovies() {
		List<? extends Movie> movies = movieService.getAllMovies();
		assertEquals(442, movies.size());
	}

	@Test
	public void getAllGenres() {
		List<? extends Genre> genres = movieService.getAllGenres();
		assertEquals(21, genres.size());
	}

	@Test
	public void getAllActors() {
		List<? extends Person> actors = movieService.getAllActors();
		assertEquals(795, actors.size());
	}

	@Test
	public void getAllDirectors() {
		List<? extends Person> directors = movieService.getAllDirectors();
		assertEquals(353, directors.size());

	}

	@Test
	public void findMoviesWithActor() throws IOException {
		List<? extends Movie> actorMovies = movieService.findMoviesWithActor("Will Smith");
		assertEquals(3, actorMovies.size());
	}

	@Test
	public void findMoviesWithDirector() {
		List<? extends Movie> movies = movieService.findMoviesWithDirector("Ron Howard");
		assertEquals(2, movies.size());
	}

	@Test
	public void findMoviesInGenre() {
		List<? extends Movie> movies = movieService.findMoviesInGenre("Comedy");
		assertEquals(122, movies.size());
	}

	@Test
	public void findByTitle() {
		List<? extends Movie> movies = movieService.findByTitle("The Hunger Games: Catching Fire");
		assertEquals(1, movies.size());
		Movie movie = movies.get(0);
		assertFalse(movie.getRating().isPresent());
	}
	
	@Test
	public void findTopMovies() {
		List<? extends Movie> movies = repositoryMovieService.findTopMovies(5);
		System.out.println(movies);
	}
}
