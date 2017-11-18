package com.tecacet.movie.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tecacet.movie.domain.Person;

@Entity
@Table(name = "person")
public class EntityPerson implements Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private final String name;
	@Column(name = "actor")
	private final boolean isActor;
	@Column(name = "director")
	private final boolean isDirector;

	public EntityPerson(String name, boolean isActor, boolean isDirector) {
		super();
		this.name = name;
		this.isActor = isActor;
		this.isDirector = isDirector;
	}

	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isActor() {
		return isActor;
	}

	@Override
	public boolean isDirector() {
		return isDirector;
	}

	@Override
	public String toString() {
		return name;
	}
}
