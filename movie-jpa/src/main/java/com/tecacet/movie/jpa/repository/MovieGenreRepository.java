package com.tecacet.movie.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.tecacet.movie.jpa.model.EntityMovie;
import com.tecacet.movie.jpa.model.MovieGenre;

public interface MovieGenreRepository extends Repository<MovieGenre, Long>{

	@Query("select mg.movie from MovieGenre mg where mg.genre.name = :genre")
	List<EntityMovie> findMoviesInGenre(@Param("genre") String genre);
	
	List<MovieGenre> findAll();
}
