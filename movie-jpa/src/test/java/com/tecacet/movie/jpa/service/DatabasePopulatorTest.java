package com.tecacet.movie.jpa.service;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.transaction.Transactional;

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
@Transactional
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
		long startTime = System.currentTimeMillis();
		databasePopulator.loadMovies();
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Time to populate = " + duration);
		assertEquals(442, movieRepository.count());
		assertEquals(1135, personRepository.count());
		assertEquals(21, genreRepository.count());

		startTime = System.currentTimeMillis();
		databasePopulator.deleteData();
		duration = System.currentTimeMillis() - startTime;
		System.out.println("Time to delete = " + duration);
		assertEquals(0, movieRepository.count());
		assertEquals(0, personRepository.count());
		assertEquals(0, genreRepository.count());
	}

}
