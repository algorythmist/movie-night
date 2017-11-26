package com.tecacet.movie.jpa.repository;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tecacet.movie.jpa.model.EntityMovie;

public interface MovieRepository extends CrudRepository<EntityMovie, Long> {

	List<EntityMovie> findAll();

	List<EntityMovie> findByTitleContainingIgnoreCase(String title);

	@Modifying
	@Transactional(value = TxType.REQUIRED)
	@Query(value = "truncate table movie_genre, movie_actor, movie_director, movie", nativeQuery = true)
	void truncate();
}
