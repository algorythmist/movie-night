package com.tecacet.movie.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tecacet.movie.entity.EntityMovie;
import com.tecacet.movie.entity.EntityPerson;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class MovieActorRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MovieActorRepository moviePersonRepository;

    @AfterEach
    public void cleanUp() {
        moviePersonRepository.deleteAll();
        movieRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    public void findMoviesWithActor() {
        EntityMovie movie1 = new EntityMovie("Elegance");
        movie1.setYear(2001);
        EntityMovie movie2 = new EntityMovie("Remorse");
        movie2.setYear(2010);
        movieRepository.saveAll(Arrays.asList(movie1, movie2));

        EntityPerson tom = new EntityPerson("Tom");
        EntityPerson dale = new EntityPerson("Dale");
        personRepository.saveAll(Arrays.asList(tom, dale));

        movie1.addDirector(tom);
        movie1.addActor(dale);
        movie2.addActor(dale);
        movieRepository.saveAll(Arrays.asList(movie1, movie2));

        List<EntityMovie> moviesWithActorTom = moviePersonRepository.findMoviesWithActor("Tom");
        assertTrue(moviesWithActorTom.isEmpty());

        List<EntityMovie> moviesWithActorDale = moviePersonRepository.findMoviesWithActor("Dale");
        assertEquals(2, moviesWithActorDale.size());
    }

}
