package com.tecacet.movie.repository;

import com.tecacet.movie.entity.EntityMovie;
import com.tecacet.movie.entity.MovieActor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieActorRepository extends JpaRepository<MovieActor, Long> {

    @Query("select mp.movie from MovieActor mp where mp.actor.name = :name")
    List<EntityMovie> findMoviesWithActor(@Param("name") String actorName);

}
