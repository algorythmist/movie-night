package com.tecacet.movie.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tecacet.movie.data.model.EntityMovie;

public interface MovieRepository extends CrudRepository<EntityMovie, Long>{

	List<EntityMovie> findAll();
}
