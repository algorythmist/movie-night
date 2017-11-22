package com.tecacet.movie.jpa.service;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.domain.Director;
import com.tecacet.movie.jpa.config.PersistenceConfiguration;
import com.tecacet.movie.service.DirectorRatingService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersistenceConfiguration.class })
@Transactional
public class NativeDirectorRatingServiceTest {

	@Autowired
	private DirectorRatingService directorRatingService;

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
	public void testFindTopDirectors() {
		List<? extends Director> directors = directorRatingService.findTopDirectors(5);
		System.out.println(directors);

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
	}

}
