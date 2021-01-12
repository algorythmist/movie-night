package com.tecacet.movie.jpa.specification;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tecacet.movie.entity.EntityMovie;
import com.tecacet.movie.integration.DatabasePopulator;
import com.tecacet.movie.repository.MovieRepository;
import com.tecacet.movie.service.jpa.specification.MovieSpecification;
import com.tecacet.movie.service.jpa.specification.SearchCriteria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class DynamicQueryIntegrationTest {

    private final AtomicBoolean loaded = new AtomicBoolean(false);
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private DatabasePopulator databasePopulator;

    @BeforeEach
    public void setUpDatabase() throws IOException {
        if (!loaded.getAndSet(true)) {
            databasePopulator.loadMovies();
        }
    }

    @Test
    public void dynamicQuery() {
        MovieSpecification olderMovies =
                new MovieSpecification(new SearchCriteria("year", SearchCriteria.Operation.LESS, 1990));
        MovieSpecification goodRating =
                new MovieSpecification(new SearchCriteria("rating", SearchCriteria.Operation.GREATER, 8.0));
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("title").descending());
        Page<EntityMovie> page = movieRepository.findAll(olderMovies.and(goodRating), pageRequest);
        assertEquals(10, page.getNumberOfElements());
        List<EntityMovie> oldiesButGoldies = page.getContent();
        assertEquals("The Wizard of Oz", oldiesButGoldies.get(0).getTitle());
        assertEquals("Il buono, il brutto, il cattivo.", oldiesButGoldies.get(9).getTitle());
    }
}
