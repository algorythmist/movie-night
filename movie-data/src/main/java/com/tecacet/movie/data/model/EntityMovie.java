package com.tecacet.movie.data.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.tecacet.movies.domain.Movie;

@Entity
public class EntityMovie implements Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;
	private int year;
	private LocalDate releaseDate;
	private String plot;
	
	public EntityMovie(String title) {
		super();
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int getYear() {
		return year;
	}

	@Override
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	@Override
	public String getPlot() {
		return plot;
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRating() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getImageUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getActors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDirectors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getGenres() {
		// TODO Auto-generated method stub
		return null;
	}

}
