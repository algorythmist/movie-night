package com.tecacet.movie.jpa.specification;

import static org.junit.Assert.assertEquals;

import com.tecacet.movie.jpa.config.PersistenceConfiguration;
import com.tecacet.movie.jpa.model.EntityMovie;
import com.tecacet.movie.jpa.repository.MovieRepository;
import com.tecacet.movie.jpa.service.DatabasePopulator;
import com.tecacet.movie.jpa.specification.SearchCriteria.Operation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceConfiguration.class})
@Transactional
public class DynamicQueryIntegrationTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DatabasePopulator databasePopulator;

    private final AtomicBoolean loaded = new AtomicBoolean(false);

    @Before
    public void setUpDatabase() throws IOException {
        if (!loaded.getAndSet(true)) {
            databasePopulator.loadMovies();
        }
    }

    @Test
    public void dynamicQuery() {
        MovieSpecification olderMovies =
                new MovieSpecification(new SearchCriteria("year", Operation.LESS, 1990));
        MovieSpecification goodRating =
                new MovieSpecification(new SearchCriteria("rating", Operation.GREATER, 8.0));
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("title").descending());
        Page<EntityMovie> page = movieRepository.findAll(olderMovies.and(goodRating), pageRequest);
        assertEquals(10, page.getNumberOfElements());
        List<EntityMovie> oldiesButGoldies = page.getContent();
        assertEquals("The Wizard of Oz", oldiesButGoldies.get(0).getTitle());
        assertEquals("Il buono, il brutto, il cattivo.", oldiesButGoldies.get(9).getTitle());
    }
}
