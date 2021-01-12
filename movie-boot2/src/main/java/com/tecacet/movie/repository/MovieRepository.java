package com.tecacet.movie.repository;

import com.tecacet.movie.entity.EntityMovie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<EntityMovie, Long>, JpaSpecificationExecutor<EntityMovie> {

    List<EntityMovie> findByTitleContainingIgnoreCase(String title);

    @Query("from EntityMovie m where rating is not null order by rating desc, title asc")
    Page<EntityMovie> findTopMovies(Pageable pageable);

}
