package com.tecacet.movie.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

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
