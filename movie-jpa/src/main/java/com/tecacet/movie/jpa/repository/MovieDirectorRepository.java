package com.tecacet.movie.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.tecacet.movie.jpa.model.EntityMovie;
import com.tecacet.movie.jpa.model.MovieDirector;

public interface MovieDirectorRepository extends Repository<MovieDirector, Long> {

	@Query("select mp.movie from MovieDirector mp where mp.director.name = :name")
	List<EntityMovie> findMoviesWithDirector(@Param("name") String directorName);
}
