package com.tecacet.movie.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie_director")
public class MovieDirector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@ManyToOne
	@JoinColumn(name = "movie_id", nullable = false)
	private final EntityMovie movie;

	@ManyToOne
	@JoinColumn(name = "director_id", nullable = false)
	private final EntityPerson director;

	public MovieDirector(EntityMovie movie, EntityPerson person) {
		super();
		this.movie = movie;
		this.director = person;
	}

	public EntityMovie getMovie() {
		return movie;
	}

	public EntityPerson getDirector() {
		return director;
	}

}
