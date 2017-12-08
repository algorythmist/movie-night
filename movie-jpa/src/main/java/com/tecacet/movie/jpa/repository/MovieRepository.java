package com.tecacet.movie.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tecacet.movie.jpa.model.EntityMovie;

public interface MovieRepository extends CrudRepository<EntityMovie, Long>, JpaSpecificationExecutor<EntityMovie> {

	List<EntityMovie> findAll();

	List<EntityMovie> findByTitleContainingIgnoreCase(String title);

	@Query("from EntityMovie m where rating is not null order by rating desc, title asc")
	Page<EntityMovie> findTopMovies(Pageable pageable);

}
