package com.tecacet.movie.jpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.jpa.config.PersistanceConfiguration;
import com.tecacet.movie.jpa.model.EntityMovie;
import com.tecacet.movie.jpa.model.EntityPerson;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersistanceConfiguration.class })
@Transactional
public class MoviePersonRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private MoviePersonRepository moviePersonRepository;

	@Test
	public void testFindMoviesWithActor() {
		EntityMovie movie1 = new EntityMovie("Elegance");
		EntityMovie movie2 = new EntityMovie("Remorse");
		movieRepository.saveAll(Arrays.asList(movie1, movie2));

		EntityPerson person1 = new EntityPerson("Tom", false, true);
		EntityPerson person2 = new EntityPerson("Dale", true, true);
		personRepository.saveAll(Arrays.asList(person1, person2));

		movie1.addPerson(person1);
		movie1.addPerson(person2);
		movie2.addPerson(person2);

		List<EntityMovie> moviesWithActorTom = moviePersonRepository.findMoviesWithActor("Tom");
		assertTrue(moviesWithActorTom.isEmpty());

		List<EntityMovie> moviesWithActorDale = moviePersonRepository.findMoviesWithActor("Dale");
		assertEquals(2, moviesWithActorDale.size());
	}

}
