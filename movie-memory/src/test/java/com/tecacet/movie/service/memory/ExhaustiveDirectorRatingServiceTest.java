package com.tecacet.movie.service.memory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.tecacet.movie.parser.MovieParser;
import com.tecacet.movie.service.MovieService;
import com.tecacet.movie.service.memory.Director;
import com.tecacet.movie.service.memory.DirectorRatingService;
import com.tecacet.movie.service.memory.ExhaustiveDirectorRatingService;
import com.tecacet.movie.service.memory.InMemoryMovieService;

public class ExhaustiveDirectorRatingServiceTest {

	@Test
	public void findTopDirectors() throws IOException {
		MovieParser movieParser = new MovieParser();
		MovieService movieService = new InMemoryMovieService(movieParser);
		DirectorRatingService ratingService = new ExhaustiveDirectorRatingService(movieService);

		List<Director> directors = ratingService.findTopDirectors(10);
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
		assertEquals(4, director.getMovies());
		assertEquals("[Comedy, Drama, Family, Romance, War]", director.getGenres().toString());
		
	}

}
