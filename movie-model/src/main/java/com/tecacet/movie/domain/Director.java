package com.tecacet.movie.domain;

import java.util.List;
import java.util.Set;

/**
 * An aggregate object for Director
 * 
 * @author dimitri
 *
 */
public interface Director {

	String getName();

	double getRating();

	Set<String> getGenres();

	List<? extends Movie> getMovies();

}