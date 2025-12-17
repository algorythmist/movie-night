package com.tecacet.movie.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.tecacet.movie.domain.Person;

@Entity
@Table(name = "person")
public class EntityPerson implements Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	
	
	@SuppressWarnings("unused") //for HB
	private EntityPerson() {
		
	}
	
	public EntityPerson(String name) {
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
