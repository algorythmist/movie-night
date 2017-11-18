package com.tecacet.movie.service.memory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tecacet.movie.domain.Director;
import com.tecacet.movie.parser.MovieParser;
import com.tecacet.movie.service.DirectorRatingService;
import com.tecacet.movie.service.MovieService;

public class DirectorRatingServiceTest {

	private final MovieParser movieParser = new MovieParser();
	private MovieService movieService;
	
	@Before
	public void setUp() throws IOException {
		movieService = new InMemoryMovieService(movieParser);
	}
	
	@Test
	public void findTopDirectorsSerial() throws IOException {
		DirectorRatingService ratingService = new ExhaustiveDirectorRatingService(movieService);
		findTopDirectors(ratingService);
	}
	
	@Test
	public void findTopDirectorsParallel8() throws IOException {
		DirectorRatingService ratingService = new Parallel8DirectorRatingService(movieService);
		findTopDirectors(ratingService);
	}
	
	@Test
	public void findTopDirectorsParallelExecutor() throws IOException {
		DirectorRatingService ratingService = new ParallelExecutorDirectorRatingService(movieService);
		findTopDirectors(ratingService);
	}
	
	private void findTopDirectors(DirectorRatingService ratingService) {
		List<? extends Director> directors = ratingService.findTopDirectors(10);
		assertEquals(10, directors.size());

		// Test that directors are in the correct order
		double lastRating = directors.get(0).getRating();
		for (int i = 1; i < 5; i++) {
			double rating = directors.get(i).getRating();
			assertTrue(rating <= lastRating);
			lastRating = rating;
		}

		Director director = directors.get(0);
		assertEquals("Charles Chaplin", director.getName());
		assertEquals(8.50, director.getRating(), 0.01);
		assertEquals(4, director.getMovies().size());
		assertEquals("[Comedy, Drama, Family, Romance, War]", director.getGenres().toString());
		
	}

}
