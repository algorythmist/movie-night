package com.tecacet.movie.service.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.config.ApplicationConfiguration;
import com.tecacet.movie.domain.Director;
import com.tecacet.movie.service.DirectorRatingService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
public class ExhaustiveDirectorRatingServiceTest {

	@Autowired
	private DirectorRatingService ratingService;

	@Test
	public void findTopDirectors() throws IOException {

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
