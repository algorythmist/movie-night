package com.tecacet.movie.jpa.service;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.jpa.config.PersistanceConfiguration;
import com.tecacet.movie.service.MovieService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersistanceConfiguration.class })
@Transactional
public class RepositoryMovieServiceIntegrationTest {

	@Autowired
	private MovieService movieService;
	
	@Test
	public void test() {
		//TODO
		movieService.getAllMovies();
	}

}
