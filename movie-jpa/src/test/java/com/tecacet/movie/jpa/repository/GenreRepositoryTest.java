package com.tecacet.movie.jpa.repository;

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

import com.tecacet.movie.jpa.config.PersistanceConfiguration;
import com.tecacet.movie.jpa.model.EntityGenre;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersistanceConfiguration.class })
@Transactional
public class GenreRepositoryTest {

	@Resource
	private GenreRepository genreRepository;

	@Test
	public void crudOperations() {

		EntityGenre genre = new EntityGenre("Exreme Action");

		genreRepository.save(genre);
		assertTrue(genre.getId() > 0);

		Optional<EntityGenre> found = genreRepository.findById(genre.getId());
		assertEquals("Exreme Action", found.get().getName());

		List<EntityGenre> allGenres = genreRepository.findAll();
		assertEquals(1, allGenres.size());

		genreRepository.delete(genre);
		allGenres = genreRepository.findAll();
		assertEquals(0, allGenres.size());

	}

}
