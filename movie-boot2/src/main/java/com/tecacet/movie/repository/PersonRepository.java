package com.tecacet.movie.repository;

import com.tecacet.movie.entity.EntityPerson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<EntityPerson, Long> {

    @Query("select distinct mp.actor from MovieActor mp")
    List<EntityPerson> findAllActors();

    @Query("select distinct mp.director from MovieDirector mp")
    List<EntityPerson> findAllDirectors();
}
