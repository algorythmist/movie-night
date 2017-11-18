package com.tecacet.movie.service;

import java.util.List;

import com.tecacet.movie.domain.Director;

public interface DirectorRatingService {

	List<? extends Director> findTopDirectors(int top);
	
}
