package com.tecacet.movie.repository;


import com.tecacet.movie.entity.EntityGenre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<EntityGenre, Long> {

    Optional<EntityGenre> findByNameIgnoreCase(String name);
}
