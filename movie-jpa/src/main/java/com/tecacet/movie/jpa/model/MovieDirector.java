package com.tecacet.movie.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
