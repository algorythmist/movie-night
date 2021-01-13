package com.tecacet.movie.client.service;

import com.tecacet.movie.client.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAllGenres();

    Genre create(String name);

    Genre findGenreById(long id);

    Genre findGenreByName(String name);

    void delete(long id);

}