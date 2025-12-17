package com.tecacet.movie.jpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.jpa.config.PersistenceConfiguration;
import com.tecacet.movie.jpa.model.EntityGenre;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersistenceConfiguration.class })
@Transactional
public class GenreRepositoryTest {

	@Autowired
	private GenreRepository genreRepository;

	@Test
	public void crudOperations() {

		String genreName = "Extreme Action";
		EntityGenre genre = new EntityGenre(genreName);

		genreRepository.save(genre);
		assertTrue(genre.getId() > 0);

		Optional<EntityGenre> found = genreRepository.findById(genre.getId());
		assertEquals(genreName, found.get().getName());
		
		found = genreRepository.findByNameIgnoreCase("extreme action");
		assertEquals(genreName, found.get().getName());

		List<EntityGenre> allGenres = genreRepository.findAll();
		assertEquals(1, allGenres.size());

		genreRepository.delete(genre);
		allGenres = genreRepository.findAll();
		assertEquals(0, allGenres.size());

	}

}
