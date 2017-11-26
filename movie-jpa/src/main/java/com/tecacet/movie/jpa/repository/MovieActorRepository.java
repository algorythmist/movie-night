package com.tecacet.movie.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.tecacet.movie.jpa.model.EntityMovie;
import com.tecacet.movie.jpa.model.MovieActor;

public interface MovieActorRepository extends Repository<MovieActor, Long> {

	@Query("select mp.movie from MovieActor mp where mp.actor.name = :name")
	List<EntityMovie> findMoviesWithActor(@Param("name") String actorName);

}
