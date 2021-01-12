package com.tecacet.movie.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.domain.Person;
import com.tecacet.movie.integration.DatabasePopulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class RepositoryMovieServiceIntegrationTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private RepositoryMovieService repositoryMovieService;

    @Autowired
    private DatabasePopulator databasePopulator;

    private final AtomicBoolean loaded = new AtomicBoolean(false);

    @BeforeEach
    public void setUpDatabase() throws IOException {
        if (!loaded.getAndSet(true)) {
            databasePopulator.loadMovies();
        }
    }

    @Test
    public void getAllMovies() {
        List<? extends Movie> movies = movieService.getAllMovies();
        assertEquals(442, movies.size());
    }

    @Test
    public void getAllGenres() {
        List<? extends Genre> genres = movieService.getAllGenres();
        assertEquals(21, genres.size());
    }

    @Test
    public void getAllActors() {
        List<? extends Person> actors = movieService.getAllActors();
        assertEquals(795, actors.size());
    }

    @Test
    public void getAllDirectors() {
        List<? extends Person> directors = movieService.getAllDirectors();
        assertEquals(353, directors.size());
    }

    @Test
    public void findMoviesWithActor() throws IOException {
        List<? extends Movie> actorMovies = movieService.findMoviesWithActor("Will Smith");
        assertEquals(3, actorMovies.size());
    }

    @Test
    public void findMoviesWithDirector() {
        List<? extends Movie> movies = movieService.findMoviesWithDirector("Ron Howard");
        assertEquals(2, movies.size());
    }

    @Test
    public void findMoviesInGenre() {
        List<? extends Movie> movies = movieService.findMoviesInGenre("Comedy");
        assertEquals(122, movies.size());
    }

    @Test
    public void findByTitle() {
        List<? extends Movie> movies = movieService.findByTitle("The Hunger Games: Catching Fire");
        assertEquals(1, movies.size());
        Movie movie = movies.get(0);
        assertFalse(movie.getRating().isPresent());
    }

    @Test
    public void findTopMovies() {
        List<? extends Movie> movies = repositoryMovieService.findTopMovies(5);
        //TODO: System.out.println(movies);
    }
}
