package com.tecacet.movies.domain;

import java.time.LocalDate;
import java.util.List;

public interface Movie {

	String getTitle();

	int getYear();

	LocalDate getReleaseDate();

	String getPlot();

	int getDuration();

	double getRating();

	String getImageUrl();
	
	List<String> getActors();

	List<String> getDirectors();

	List<String> getGenres();

}