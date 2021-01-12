package com.tecacet.movie.repository;


import com.tecacet.movie.entity.EntityMovie;
import com.tecacet.movie.entity.MovieDirector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieDirectorRepository extends JpaRepository<MovieDirector, Long> {

    @Query("select mp.movie from MovieDirector mp where mp.director.name = :name")
    List<EntityMovie> findMoviesWithDirector(@Param("name") String directorName);
}
