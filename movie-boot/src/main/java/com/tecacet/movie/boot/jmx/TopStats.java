package com.tecacet.movie.boot.jmx;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import com.tecacet.movie.domain.Director;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.jpa.service.JPQLDirectorRatingService;
import com.tecacet.movie.jpa.service.RepositoryMovieService;
import com.tecacet.movie.service.DirectorRatingService;

@Component
@ManagedResource(objectName = "movieStats:name=top")
public class TopStats {
	
	private final RepositoryMovieService movieService;
	private final DirectorRatingService directorRatingService;

	@Autowired
	public TopStats(RepositoryMovieService movieService,  JPQLDirectorRatingService directorRatingService) {
		super();
		this.movieService = movieService;
		this.directorRatingService = directorRatingService;
	}

	@ManagedOperation
	public synchronized List<String> getTopMovies(int number) {
		if (number > 10) {
			throw new IllegalArgumentException("Can only return up to top 10");
		}
		return movieService.findTopMovies(number).stream().map(Movie::toString).collect(Collectors.toList());
	}
	
	@ManagedOperation
	public synchronized List<String> getTopDirectors(int number) {
		if (number > 10) {
			throw new IllegalArgumentException("Can only return up to top 10");
		}
		return directorRatingService.findTopDirectors(number).stream().map(Director::toString).collect(Collectors.toList());
	}
	
}
