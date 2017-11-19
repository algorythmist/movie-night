package com.tecacet.movie.jpa.repository;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

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
@Transactional
public class MovieGenreRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private MovieGenreRepository movieGenreRepository;
	
	@Test
	public void findMoviesInGenre() {
		
		EntityGenre genre1 = new EntityGenre("Romance");
		EntityGenre genre2 = new EntityGenre("Drama");
		genreRepository.saveAll(Arrays.asList(genre1, genre2));
		
		EntityMovie movie1 = new EntityMovie("Elegance");
		EntityMovie movie2 = new EntityMovie("Remorse");
		EntityMovie movie3 = new EntityMovie("Passion");
		movie1.addGenre(genre1);
		movie1.addGenre(genre2);
		movie2.addGenre(genre2);
		movie3.addGenre(genre1);
		movieRepository.saveAll(Arrays.asList(movie1, movie2, movie3));
		
		List<EntityMovie> romanceMovies = movieGenreRepository.findMoviesInGenre("Romance");
		assertEquals(2, romanceMovies.size());
		System.out.println(romanceMovies); //TODO
	
		List<EntityMovie> dramaMovies = movieGenreRepository.findMoviesInGenre("Drama");
		assertEquals(2, dramaMovies.size());
		System.out.println(dramaMovies); //TODO
	}

}
