package com.tecacet.movie.service;



import com.tecacet.movie.entity.EntityGenre;
import com.tecacet.movie.entity.EntityMovie;
import com.tecacet.movie.entity.EntityPerson;
import com.tecacet.movie.repository.GenreRepository;
import com.tecacet.movie.repository.MovieActorRepository;
import com.tecacet.movie.repository.MovieDirectorRepository;
import com.tecacet.movie.repository.MovieGenreRepository;
import com.tecacet.movie.repository.MovieRepository;
import com.tecacet.movie.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<EntityMovie> findTopMovies(int number) {
        Pageable pageable = PageRequest.of(0, number);
        Page<EntityMovie> page = movieRepository.findTopMovies(pageable);
        return page.getContent();
    }
}
