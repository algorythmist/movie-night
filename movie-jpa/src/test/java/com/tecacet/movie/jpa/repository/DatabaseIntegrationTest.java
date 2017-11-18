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
import com.tecacet.movie.jpa.model.EntityGenre;
import com.tecacet.movie.jpa.model.EntityMovie;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {PersistanceConfiguration.class})
@Transactional
public class DatabaseIntegrationTest {

	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private MovieRepository movieRepository;
	
	@Test
	public void movieWithGenres() {
		EntityGenre action = new EntityGenre("Action");
		EntityGenre comedy = new EntityGenre("Comedy");
		genreRepository.save(action);
		genreRepository.save(comedy);
		
		EntityMovie movie = new EntityMovie("Elegance");
		movie.setDuration(189);
		movie.setReleaseDate(LocalDate.of(2012, 3, 4));
		movie.addGenre(action);
		movie.addGenre(comedy);
		movieRepository.save(movie);
		assertTrue(movie.getId() > 0);
		
		Optional<EntityMovie> optional = movieRepository.findById(movie.getId());
		EntityMovie found = optional.get();
		assertEquals("Elegance", found.getTitle());
		System.out.println(found.getGenres()); //TODO
		
		movieRepository.delete(found);
		
		List<EntityGenre> genres = genreRepository.findAll();
		System.out.println(genres); //TODO
	}
}
