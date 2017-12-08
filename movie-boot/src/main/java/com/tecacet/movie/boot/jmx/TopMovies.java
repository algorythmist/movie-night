package com.tecacet.movie.boot.jmx;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.jpa.service.RepositoryMovieService;

@Component
@ManagedResource(objectName = "movieStats:name=topMovies")
public class TopMovies {
	
	private final RepositoryMovieService movieService;

	public TopMovies(RepositoryMovieService movieService) {
		super();
		this.movieService = movieService;
	}

	@ManagedOperation
	public synchronized List<String> getTopMovies(int number) {
		if (number > 10) {
			throw new RuntimeException("Too many");
		}
		return movieService.findTopMovies(number).stream().map(Movie::toString).collect(Collectors.toList());
	}
	
}
