package com.tecacet.movie.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecacet.movie.jpa.model.EntityMovie;
import com.tecacet.movie.jpa.repository.MovieRepository;

@Service
public class MovieFacade {

	private final MovieRepository movieRepository;

	@Autowired
	public MovieFacade(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}
	
	public List<EntityMovie> findAll() {
		return movieRepository.findAll();
	}
	
	public Optional<EntityMovie> findMovieById(Long id) {
		return movieRepository.findById(id);
	}
	
	public void save(EntityMovie movie) {
		movieRepository.save(movie);
	}
}
