package com.tecacet.movie.jpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.jpa.config.PersistenceConfiguration;
import com.tecacet.movie.jpa.model.EntityMovie;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersistenceConfiguration.class })
@Transactional
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;

	@Test
	public void crudOperations() {
		List<EntityMovie> movies = movieRepository.findAll();
		assertTrue(movies.isEmpty());

		EntityMovie movie = createMovie();
		movieRepository.save(movie);
		assertTrue(movie.getId() > 0);

		Optional<EntityMovie> found = movieRepository.findById(movie.getId());
		EntityMovie entityMovie = found.get();
		assertEquals("Elegance", entityMovie.getTitle());
		assertEquals("A pointless waste of time", entityMovie.getPlot());
		assertEquals("x", entityMovie.getImageUrl());
		assertEquals(2002, entityMovie.getYear());
		assertEquals(90, entityMovie.getDuration());
		assertEquals(LocalDate.of(2012, 3, 4), entityMovie.getReleaseDate());
		assertEquals(1.2, entityMovie.getRating().orElse(null), 0.001);
		
		
		List<EntityMovie> byTitle = movieRepository.findByTitleContainingIgnoreCase("elegance");
		assertEquals(1, byTitle.size());

		List<EntityMovie> allMovies = movieRepository.findAll();
		assertEquals(1, allMovies.size());

		movieRepository.delete(movie);
		allMovies = movieRepository.findAll();
		assertEquals(0, allMovies.size());

	}

	private EntityMovie createMovie() {
		EntityMovie movie = new EntityMovie("Elegance");
		movie.setYear(2002);
		movie.setDuration(189);
		movie.setReleaseDate(LocalDate.of(2012, 3, 4));
		movie.setDuration(90);
		movie.setImageUrl("x");
		movie.setPlot("A pointless waste of time");
		movie.setRating(1.2);
		return movie;
	}

}
