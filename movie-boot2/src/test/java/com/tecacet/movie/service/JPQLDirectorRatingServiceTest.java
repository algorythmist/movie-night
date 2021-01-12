package com.tecacet.movie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tecacet.movie.domain.Director;
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
public class JPQLDirectorRatingServiceTest {

    @Autowired
    private JPQLDirectorRatingService directorRatingService;

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
    public void testFindTopDirectors() {
        List<? extends Director> directors = directorRatingService.findTopDirectors(5);
        assertEquals(5, directors.size());

        // Test that directors are in the correct order
        double lastRating = directors.get(0).getRating();
        for (int i = 1; i < 5; i++) {
            double rating = directors.get(i).getRating();
            assertTrue(rating <= lastRating);
            lastRating = rating;
        }

        Director director = directors.get(0);
        assertEquals("Christopher Nolan", director.getName());
        assertEquals(8.62, director.getRating(), 0.01);
        assertEquals(6, director.getMovies());
    }

}
