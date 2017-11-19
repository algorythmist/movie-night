package com.tecacet.movie.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tecacet.movie.jpa.model.EntityPerson;

public interface PersonRepository extends CrudRepository<EntityPerson, Long> {

	List<EntityPerson> findAll();

	@Query("select distinct mp.actor from MovieActor mp")
	List<EntityPerson> findAllActors();
	
	@Query("select distinct mp.director from MovieDirector mp")
	List<EntityPerson> findAllDirectors();
}
