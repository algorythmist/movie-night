package com.tecacet.movie.jpa.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.jpa.config.PersistenceConfiguration;
import com.tecacet.movie.jpa.repository.GenreRepository;
import com.tecacet.movie.jpa.repository.MovieRepository;
import com.tecacet.movie.jpa.repository.PersonRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersistenceConfiguration.class })
public class DatabasePopulatorTest {

	@Autowired
	private DatabasePopulator databasePopulator;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void test() throws IOException {
		databasePopulator.loadMovies();
		assertEquals(4609, movieRepository.count());
		assertEquals(7426, personRepository.count());
		assertEquals(24, genreRepository.count());
		
		databasePopulator.deleteData();
		assertEquals(0, movieRepository.count());
		assertEquals(0, personRepository.count());
		assertEquals(0, genreRepository.count());
	}

}
