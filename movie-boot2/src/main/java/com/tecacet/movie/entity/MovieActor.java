package com.tecacet.movie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie_actor")
public class MovieActor {

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private EntityMovie movie;
    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false)
    private EntityPerson actor;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    private MovieActor() {
    }

    public MovieActor(EntityMovie movie, EntityPerson person) {
        super();
        this.movie = movie;
        this.actor = person;
    }

    public EntityMovie getMovie() {
        return movie;
    }

    public EntityPerson getActor() {
        return actor;
    }

}
