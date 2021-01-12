package com.tecacet.movie.service;

import com.tecacet.movie.domain.Director;

import java.util.List;

public interface DirectorRatingService {

    List<? extends Director> findTopDirectors(int top);

}
