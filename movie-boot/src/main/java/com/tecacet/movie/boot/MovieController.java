package com.tecacet.movie.boot;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.domain.Person;
import com.tecacet.movie.parser.JsonMovie;
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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<? extends Movie> getAllMovies() {
		return movieService.getAllMovies();
	}
	
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
	public Movie findMovieById(@PathVariable long id) {
		return new Movie() {
			
			@Override
			public int getYear() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getTitle() {
				return "hello";
			}
			
			@Override
			public LocalDate getReleaseDate() {
				return LocalDate.now();
			}
			
			@Override
			public Optional<Double> getRating() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getPlot() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getImageUrl() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<? extends Genre> getGenres() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getDuration() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public List<? extends Person> getDirectors() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<? extends Person> getActors() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
}
