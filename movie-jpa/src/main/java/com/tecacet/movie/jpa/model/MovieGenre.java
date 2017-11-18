package com.tecacet.movie.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tecacet.movie.domain.Movie;

@Entity
@Table(name = "movie_genre")
public class MovieGenre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "movie_id", nullable = false)
	private final EntityMovie movie;

	@ManyToOne
	@JoinColumn(name = "genre_id", nullable = false)
	private final EntityGenre genre;

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
