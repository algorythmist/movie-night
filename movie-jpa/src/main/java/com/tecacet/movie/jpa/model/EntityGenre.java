package com.tecacet.movie.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.tecacet.movie.domain.Genre;

@Entity
@Table(name = "genre")
public class EntityGenre implements Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	private String name;

	//for HB
	@SuppressWarnings("unused")
	private EntityGenre() {
		
	}
	
	public EntityGenre(String name) {
		super();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
