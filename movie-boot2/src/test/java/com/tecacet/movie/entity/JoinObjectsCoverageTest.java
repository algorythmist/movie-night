package com.tecacet.movie.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JoinObjectsCoverageTest {

    @Test
    public void test() {
        EntityMovie movie = new EntityMovie("Test");
        EntityGenre genre = new EntityGenre("Fun");
        MovieGenre movieGenre = new MovieGenre(movie, genre);
        assertEquals(movie, movieGenre.getMovie());
        assertEquals(genre, movieGenre.getGenre());

        EntityPerson person = new EntityPerson("Dude");
        MovieActor movieActor = new MovieActor(movie, person);
        assertEquals(movie, movieActor.getMovie());
        assertEquals(person, movieActor.getActor());
        MovieDirector movieDirector = new MovieDirector(movie, person);
        assertEquals(movie, movieDirector.getMovie());
        assertEquals(person, movieDirector.getDirector());
    }

}
