package com.tecacet.movie.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tecacet.movie.jpa.model.EntityGenre;

public interface GenreRepository extends CrudRepository<EntityGenre, Long> {

	List<EntityGenre> findAll();
	
	Optional<EntityGenre> findByNameIgnoreCase(String name);
}
