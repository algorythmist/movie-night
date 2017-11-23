package com.tecacet.movie.boot.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecacet.movie.jpa.service.DatabasePopulator;

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
	 * TODO hande exception
	 * @throws MovieServiceException 
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/populate", method = RequestMethod.POST)
	public void populateData() throws MovieServiceException {
		try {
			databasePopulator.loadMovies();
		} catch (Exception e) {
			throw new MovieServiceException(e);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteData() {
		databasePopulator.deleteData();
	}
}
