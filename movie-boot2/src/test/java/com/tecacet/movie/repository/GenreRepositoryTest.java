package com.tecacet.movie.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tecacet.movie.entity.EntityGenre;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @AfterEach
    public void cleanUp() {
        genreRepository.deleteAll();
    }

    @Test
    public void crudOperations() {

        String genreName = "Extreme Action";
        EntityGenre genre = new EntityGenre(genreName);

        genreRepository.save(genre);
        assertTrue(genre.getId() > 0);

        Optional<EntityGenre> found = genreRepository.findById(genre.getId());
        assertEquals(genreName, found.get().getName());

        found = genreRepository.findByNameIgnoreCase("extreme action");
        assertEquals(genreName, found.get().getName());

        List<EntityGenre> allGenres = genreRepository.findAll();
        assertEquals(1, allGenres.size());

        genreRepository.delete(genre);
        allGenres = genreRepository.findAll();
        assertEquals(0, allGenres.size());

    }
}