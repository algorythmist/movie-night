package com.tecacet.movie.boot;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecacet.movie.boot.service.MovieFacade;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.jpa.service.DatabasePopulator;

//TODO create movie
//TODO delete movie
//TODO findMovies where name like
//TODO findMovies with actor name like
//TODO findMovies with director name like
//TODO find movies for director
//TODO find movies for actor


@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	private final MovieFacade movieFacade;
	private final DatabasePopulator databasePopulator;

	@Autowired
	public MovieController(MovieFacade movieFacade, DatabasePopulator databasePopulator) {
		super();
		this.movieFacade = movieFacade;
		this.databasePopulator = databasePopulator;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<? extends Movie> getAllMovies() {
		return movieFacade.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Movie findMovieById(@PathVariable long id) {
		Optional<? extends Movie> optional = movieFacade.findMovieById(id);
		return optional.orElse(null);
	}

	/**
	 * TODO hande exception
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/populate", method = RequestMethod.POST)
	public void populateDatabase() throws IOException {
		databasePopulator.loadMovies();
	}

}
