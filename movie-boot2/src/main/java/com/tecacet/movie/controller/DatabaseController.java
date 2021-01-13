package com.tecacet.movie.controller;

import com.tecacet.movie.service.DatabasePopulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class DatabaseController {

    private final DatabasePopulator databasePopulator;

    @Autowired
    public DatabaseController(DatabasePopulator databasePopulator) {
        super();
        this.databasePopulator = databasePopulator;
    }

    /**
     * Populate the movie database from a JSON file
     *
     * @throws MovieServiceException
     */
    @RequestMapping(value = "/populate", method = RequestMethod.POST)
    public void populateData() throws MovieServiceException {
        try {
            databasePopulator.loadMovies();
        } catch (Exception e) {
            throw new MovieServiceException(e);
        }
    }

    /**
     * Delete all the data in the movie database
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteData() {
        databasePopulator.deleteData();
    }
}
