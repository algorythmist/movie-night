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

import com.tecacet.movie.jpa.config.PersistanceConfiguration;
import com.tecacet.movie.jpa.model.EntityMovie;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersistanceConfiguration.class })
@Transactional
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;

	@Test
	public void crudOperations() {
		List<EntityMovie> movies = movieRepository.findAll();
		assertTrue(movies.isEmpty());

		EntityMovie movie = new EntityMovie("Elegance");
		movie.setDuration(189);
		movie.setReleaseDate(LocalDate.of(2012, 3, 4));
		movieRepository.save(movie);
		assertTrue(movie.getId() > 0);

		Optional<EntityMovie> found = movieRepository.findById(movie.getId());
		assertEquals("Elegance", found.get().getTitle());
		
		List<EntityMovie> byTitle = movieRepository.findByTitleContainingIgnoreCase("elegance");
		assertEquals(1, byTitle.size());

		List<EntityMovie> allMovies = movieRepository.findAll();
		assertEquals(1, allMovies.size());

		movieRepository.delete(movie);
		allMovies = movieRepository.findAll();
		assertEquals(0, allMovies.size());

	}

}
