package com.tecacet.movie.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.domain.Person;

@Entity
@Table(name = "movie_person")
public class MoviePerson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name="movie_id", nullable=false)
	private final EntityMovie movie;

	@ManyToOne
	@JoinColumn(name="person_id", nullable=false)
	private final EntityPerson person;

	public MoviePerson(EntityMovie movie, EntityPerson person) {
		super();
		this.movie = movie;
		this.person = person;
	}

	public Movie getMovie() {
		return movie;
	}

	public Person getPerson() {
		return person;
	}
	
	

}
