package com.tecacet.movie.service.memory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.domain.Person;
import com.tecacet.movie.parser.JsonMovie;
import com.tecacet.movie.parser.MovieParser;
import com.tecacet.movie.service.MovieService;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class InMemoryMovieServiceTest {

    private static final MovieParser movieParser = new MovieParser();
    private static MovieService movieService;

    @BeforeClass
    public static void setUp() throws IOException {
        List<JsonMovie> movies = movieParser.parse("moviedata.json");
        movieService = new InMemoryMovieService(movies);
    }

    @Test
    public void getAllMovies() {
        List<? extends Movie> movies = movieService.getAllMovies();
        assertEquals(4609, movies.size());
    }

    @Test
    public void getAllGenres() {
        List<? extends Genre> genres = movieService.getAllGenres();
        assertEquals(24, genres.size());
    }

    @Test
    public void getAllActors() {
        List<? extends Person> actors = movieService.getAllActors();
        assertEquals(5265, actors.size());

    }

    @Test
    public void getAllDirectors() {
        List<? extends Person> directors = movieService.getAllDirectors();
        assertEquals(2333, directors.size());

    }

    @Test
    public void findMoviesWithActor() {
        List<? extends Movie> actorMovies = movieService.findMoviesWithActor("Laurence Olivier");
        assertEquals(6, actorMovies.size());
        assertEquals(
                "[Spartacus (1960): 8.0, Clash of the Titans (1981): 6.7, Rebecca (1940): 8.3, The Bounty (1984): 6.9, Marathon Man (1976): 7.5, The Prince and the Showgirl (1957): 6.5]",
                actorMovies.toString());
    }

    @Test
    public void findMoviesWithDirector() {
        List<? extends Movie> movies = movieService.findMoviesWithDirector("Ron Howard");
        assertEquals(20, movies.size());
    }

    @Test
    public void findMoviesInGenre() {
        List<? extends Movie> movies = movieService.findMoviesInGenre("News");
        assertEquals(1, movies.size());
        Movie movie = movies.get(0);
        assertEquals("Red Obsession", movie.getTitle());
    }

    @Test
    public void findByTitle() {
        List<? extends Movie> movies = movieService.findByTitle("The Hunger Games: Catching Fire");
        assertEquals(1, movies.size());
        Movie movie = movies.get(0);
        assertFalse(movie.getRating().isPresent());
    }
}
