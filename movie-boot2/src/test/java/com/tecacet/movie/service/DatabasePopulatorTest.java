package com.tecacet.movie.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tecacet.movie.repository.GenreRepository;
import com.tecacet.movie.repository.MovieRepository;
import com.tecacet.movie.repository.PersonRepository;
import com.tecacet.movie.service.DatabasePopulator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class DatabasePopulatorTest {

    @Autowired
    private DatabasePopulator databasePopulator;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void test() throws IOException {
        long startTime = System.currentTimeMillis();
        databasePopulator.loadMovies();
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Time to populate = " + duration);
        assertEquals(442, movieRepository.count());
        assertEquals(1135, personRepository.count());
        assertEquals(21, genreRepository.count());

        startTime = System.currentTimeMillis();
        databasePopulator.deleteData();
        duration = System.currentTimeMillis() - startTime;
        System.out.println("Time to delete = " + duration);
        assertEquals(0, movieRepository.count());
        assertEquals(0, personRepository.count());
        assertEquals(0, genreRepository.count());
    }

}
