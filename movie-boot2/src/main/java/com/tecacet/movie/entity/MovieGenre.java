package com.tecacet.movie.entity;

import com.tecacet.movie.domain.Movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie_genre")
public class MovieGenre {

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private EntityMovie movie;
    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private EntityGenre genre;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private MovieGenre() {
    }

    public MovieGenre(EntityMovie movie, EntityGenre genre) {
        super();
        this.movie = movie;
        this.genre = genre;
    }

    public Movie getMovie() {
        return movie;
    }

    public EntityGenre getGenre() {
        return genre;
    }

}
