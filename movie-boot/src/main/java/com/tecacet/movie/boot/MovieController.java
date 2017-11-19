package com.tecacet.movie.boot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.service.MovieService;

@RestController
@RequestMapping(value="/movies")
public class MovieController {

	private final MovieService movieService;

	@Autowired
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public List<? extends Movie> getAllMovies() {
		return movieService.getAllMovies();
	}
}
