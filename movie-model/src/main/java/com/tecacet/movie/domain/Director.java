package com.tecacet.movie.domain;

import java.util.Set;

/**
 * An aggregate object for Director
 *
 * @author dimitri
 */
public interface Director {

    String getName();

    double getRating();

    Set<String> getGenres();

    int getMovies();

}