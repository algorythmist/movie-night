package com.tecacet.movie.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface Movie {

	String getTitle();

	int getYear();

	LocalDate getReleaseDate();

	String getPlot();

	int getDuration();

	Optional<Double> getRating();

	String getImageUrl();
	
	List<? extends Person> getActors();

	List<? extends Person> getDirectors();

	List<? extends Genre> getGenres();

}