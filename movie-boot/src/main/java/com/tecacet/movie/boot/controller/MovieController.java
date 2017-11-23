package com.tecacet.movie.boot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecacet.movie.boot.domain.SimpleMovie;
import com.tecacet.movie.boot.service.MovieFacade;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.jpa.model.EntityMovie;
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

	@Autowired
	public MovieController(MovieFacade movieFacade, DatabasePopulator databasePopulator) {
		super();
		this.movieFacade = movieFacade;
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

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Movie modify(@PathVariable long id, @RequestBody SimpleMovie movie) throws ResourceNotFoundException {
		EntityMovie entityMovie = movieFacade.findMovieById(id).orElseThrow(() -> new ResourceNotFoundException());
		// copy attributes
		BeanUtils.copyProperties(movie, entityMovie);
		movieFacade.save(entityMovie);
		return entityMovie;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Movie create(@RequestBody SimpleMovie movie) {
		EntityMovie entityMovie = new EntityMovie(movie);
		movieFacade.save(entityMovie);
		return entityMovie;
	}

}
