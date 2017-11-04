package com.tecacet.movie.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.data.PersistanceConfiguration;
import com.tecacet.movie.data.model.EntityMovie;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {PersistanceConfiguration.class})
@Transactional
public class MovieRepositoryTest {

	@Resource
	private MovieRepository movieRepository;

	@Test
	public void testFindAll() {
		List<EntityMovie> movies = movieRepository.findAll();
		assertTrue(movies.isEmpty());
		
		EntityMovie movie = new EntityMovie("Elegance");
		movieRepository.save(movie);
		assertTrue(movie.getId() > 0);
		
		Optional<EntityMovie> found = movieRepository.findById(movie.getId());
		assertEquals("Elegance", found.get().getTitle());
		
	}

}
