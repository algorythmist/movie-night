package com.tecacet.movie.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecacet.movie.jpa.model.EntityGenre;
import com.tecacet.movie.jpa.model.EntityMovie;
import com.tecacet.movie.jpa.model.EntityPerson;
import com.tecacet.movie.jpa.repository.GenreRepository;
import com.tecacet.movie.jpa.repository.MovieActorRepository;
import com.tecacet.movie.jpa.repository.MovieDirectorRepository;
import com.tecacet.movie.jpa.repository.MovieGenreRepository;
import com.tecacet.movie.jpa.repository.MovieRepository;
import com.tecacet.movie.jpa.repository.PersonRepository;
import com.tecacet.movie.service.MovieService;

@Service
public class RepositoryMovieService implements MovieService {

	private final MovieRepository movieRepository;
	private final PersonRepository personRepository;
	private final GenreRepository genreRepository;
	private final MovieActorRepository movieActorRepository;
	private final MovieDirectorRepository movieDirectorRepository;
	private final MovieGenreRepository movieGenreRepository;

	@Autowired
	public RepositoryMovieService(MovieRepository movieRepository, PersonRepository personRepository,
			GenreRepository genreRepository, MovieActorRepository movieActorRepository,
			MovieDirectorRepository movieDirectorRepository, MovieGenreRepository movieGenreRepository) {
		super();
		this.movieRepository = movieRepository;
		this.personRepository = personRepository;
		this.genreRepository = genreRepository;
		this.movieActorRepository = movieActorRepository;
		this.movieDirectorRepository = movieDirectorRepository;
		this.movieGenreRepository = movieGenreRepository;
	}

	@Override
	public List<EntityMovie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public List<EntityPerson> getAllActors() {
		return personRepository.findAllActors();
	}

	@Override
	public List<EntityPerson> getAllDirectors() {
		return personRepository.findAllDirectors();
	}

	@Override
	public List<EntityGenre> getAllGenres() {
		return genreRepository.findAll();
	}

	@Override
	public List<EntityMovie> findByTitle(String title) {
		return movieRepository.findByTitleContainingIgnoreCase(title);
	}

	@Override
	public List<EntityMovie> findMoviesWithActor(String actorName) {
		return movieActorRepository.findMoviesWithActor(actorName);
	}

	@Override
	public List<EntityMovie> findMoviesWithDirector(String directorName) {
		return movieDirectorRepository.findMoviesWithDirector(directorName);
	}

	@Override
	public List<EntityMovie> findMoviesInGenre(String genreName) {
		return movieGenreRepository.findMoviesInGenre(genreName);
	}

}
