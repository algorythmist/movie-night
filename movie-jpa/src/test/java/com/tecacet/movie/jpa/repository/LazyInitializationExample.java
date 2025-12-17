package com.tecacet.movie.jpa.repository;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import jakarta.transaction.Transactional;

import org.hibernate.LazyInitializationException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.jpa.config.PersistenceConfiguration;
import com.tecacet.movie.jpa.model.EntityGenre;
import com.tecacet.movie.jpa.model.EntityMovie;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersistenceConfiguration.class })
public class LazyInitializationExample {

	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private MovieRepository movieRepository;

	@After
	public void delete() {
		movieRepository.deleteAll();
		genreRepository.deleteAll();
	}

	@Test(expected = LazyInitializationException.class)
	public void lazyInitializationException() {
		EntityGenre action = new EntityGenre("Action");
		EntityGenre comedy = new EntityGenre("Comedy");
		genreRepository.save(action);
		genreRepository.save(comedy);

		EntityMovie movie = new EntityMovie("Elegance");
		movie.setDuration(189);
		movie.setYear(2001);
		movie.setReleaseDate(LocalDate.of(2012, 3, 4));
		movie.addGenre(action);
		movie.addGenre(comedy);
		movieRepository.save(movie);
		assertTrue(movie.getId() > 0);

		EntityMovie found = findMovie();
		found.getGenres().get(0);
	}

	@Transactional
	private EntityMovie findMovie() {
		return movieRepository.findByTitleContainingIgnoreCase("Elegance").get(0);
	}

}
