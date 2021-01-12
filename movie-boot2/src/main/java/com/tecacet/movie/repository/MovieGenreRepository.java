package com.tecacet.movie.repository;

import com.tecacet.movie.entity.EntityMovie;
import com.tecacet.movie.entity.MovieGenre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {

    @Query("select mg.movie from MovieGenre mg where mg.genre.name = :genre")
    List<EntityMovie> findMoviesInGenre(@Param("genre") String genre);

}
