package com.tecacet.movie.service.memory;

import java.util.List;

public interface DirectorRatingService {

	List<Director> findTopDirectors(int top);
	
}
