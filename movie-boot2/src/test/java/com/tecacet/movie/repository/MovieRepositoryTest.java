package com.tecacet.movie.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tecacet.movie.entity.EntityMovie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @AfterEach
    public void cleanUp() {
        movieRepository.deleteAll();
    }

    @Test
    public void crudOperations() {
        List<EntityMovie> movies = movieRepository.findAll();
        assertTrue(movies.isEmpty());

        EntityMovie movie = createMovie(1.2);
        movieRepository.save(movie);
        assertTrue(movie.getId() > 0);

        Optional<EntityMovie> found = movieRepository.findById(movie.getId());
        EntityMovie entityMovie = found.get();
        assertEquals("Elegance", entityMovie.getTitle());
        assertEquals("A pointless waste of time", entityMovie.getPlot());
        assertEquals("x", entityMovie.getImageUrl());
        assertEquals(2002, entityMovie.getYear());
        assertEquals(90, entityMovie.getDuration());
        assertEquals(LocalDate.of(2012, 3, 4), entityMovie.getReleaseDate());
        assertEquals(1.2, entityMovie.getRating().get(), 0.001);

        List<EntityMovie> byTitle = movieRepository.findByTitleContainingIgnoreCase("elegance");
        assertEquals(1, byTitle.size());

        List<EntityMovie> allMovies = movieRepository.findAll();
        assertEquals(1, allMovies.size());

        movieRepository.delete(movie);
        allMovies = movieRepository.findAll();
        assertEquals(0, allMovies.size());
    }

    @Test
    public void validation() {

        EntityMovie movie = createMovie(1.2);
        movie.setYear(1890);
        movie.setDuration(-10);
        assertThrows(ConstraintViolationException.class,
                () -> movieRepository.save(movie));
    }

    @Test
    public void findTopMovies() {
        movieRepository.saveAll(Arrays.asList(
                createMovie(1.2),
                createMovie(3.2),
                createMovie(5.0),
                createMovie(2.5),
                createMovie(4.1)
        ));
        Page<EntityMovie> page = movieRepository.findTopMovies(PageRequest.of(0, 3));
        List<EntityMovie> movies = page.toList();
        assertEquals(3, movies.size());
        assertEquals(5.0, movies.get(0).getRating().get(), 0.01);
        assertEquals(4.1, movies.get(1).getRating().get(), 0.01);
        assertEquals(3.2, movies.get(2).getRating().get(), 0.01);
    }

    private EntityMovie createMovie(double rating) {
        EntityMovie movie = new EntityMovie("Elegance");
        movie.setYear(2002);
        movie.setDuration(189);
        movie.setReleaseDate(LocalDate.of(2012, 3, 4));
        movie.setDuration(90);
        movie.setImageUrl("x");
        movie.setPlot("A pointless waste of time");
        movie.setRating(rating);
        return movie;
    }

}
