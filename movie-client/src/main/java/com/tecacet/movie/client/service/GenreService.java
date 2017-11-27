package com.tecacet.movie.client.service;

import java.util.List;

import com.tecacet.movie.client.model.Genre;

public interface GenreService {

	List<Genre> getAllGenres();

	Genre create(String name);

	Genre findGenreById(long id);

	Genre findGenreByName(String name);

	void delete(long id);

}