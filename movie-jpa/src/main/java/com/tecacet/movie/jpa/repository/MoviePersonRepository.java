package com.tecacet.movie.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tecacet.movie.jpa.model.EntityMovie;
import com.tecacet.movie.jpa.model.MoviePerson;

public interface MoviePersonRepository extends CrudRepository<MoviePerson, Long> {

	@Query("select mp.movie from MoviePerson mp where mp.person.name = :name and mp.person.isActor is true")
	List<EntityMovie> findMoviesWithActor(@Param("name") String actorName);
	
	@Query("select mp.movie from MoviePerson mp where mp.person.name = :name and mp.person.isDirector is true")
	List<EntityMovie> findMoviesWithDirector(@Param("name") String directorName);
}
