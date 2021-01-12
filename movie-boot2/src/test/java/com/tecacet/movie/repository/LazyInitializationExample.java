package com.tecacet.movie.repository;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tecacet.movie.entity.EntityGenre;
import com.tecacet.movie.entity.EntityMovie;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import javax.transaction.Transactional;

@SpringBootTest
public class LazyInitializationExample {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieRepository movieRepository;

    @AfterEach
    public void delete() {
        movieRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    public void lazyInitializationException() {
        EntityGenre action = new EntityGenre("Action");
        EntityGenre comedy = new EntityGenre("Comedy");
        genreRepository.save(action);
        genreRepository.save(comedy);

        EntityMovie movie = new EntityMovie("Elegance");
        movie.setDuration(189);
        movie.setYear(2001);
        movie.setReleaseDate(LocalDate.of(2012, 3, 4));
        movie.addGenre(action);
        movie.addGenre(comedy);
        movieRepository.save(movie);
        assertTrue(movie.getId() > 0);

        EntityMovie found = findMovie();
        assertThrows(LazyInitializationException.class,
                () -> found.getGenres().get(0));
    }

    @Transactional
    private EntityMovie findMovie() {
        return movieRepository.findByTitleContainingIgnoreCase("Elegance").get(0);
    }

}
