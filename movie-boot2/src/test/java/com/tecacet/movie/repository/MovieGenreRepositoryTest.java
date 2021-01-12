package com.tecacet.movie.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tecacet.movie.entity.EntityGenre;
import com.tecacet.movie.entity.EntityMovie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class MovieGenreRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieGenreRepository movieGenreRepository;

    @AfterEach
    public void cleanUp() {
        movieGenreRepository.deleteAll();
        movieRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    public void findMoviesInGenre() {

        EntityGenre genre1 = new EntityGenre("Romance");
        EntityGenre genre2 = new EntityGenre("Drama");
        genreRepository.saveAll(Arrays.asList(genre1, genre2));

        EntityMovie movie1 = new EntityMovie("Elegance");
        EntityMovie movie2 = new EntityMovie("Remorse");
        EntityMovie movie3 = new EntityMovie("Passion");

        movie1.addGenre(genre1);
        movie1.addGenre(genre2);
        movie2.addGenre(genre2);
        movie3.addGenre(genre1);
        movieRepository.saveAll(Arrays.asList(movie1, movie2, movie3));

        List<EntityMovie> romanceMovies = movieGenreRepository.findMoviesInGenre("Romance");
        assertEquals(2, romanceMovies.size());
        System.out.println(romanceMovies); //TODO

        List<EntityMovie> dramaMovies = movieGenreRepository.findMoviesInGenre("Drama");
        assertEquals(2, dramaMovies.size());
        System.out.println(dramaMovies); //TODO
    }

}
