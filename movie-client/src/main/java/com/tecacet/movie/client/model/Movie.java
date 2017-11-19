package com.tecacet.movie.client.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

	private String title;
	private int year;
	private LocalDate releaseDate;
	private Double rating;
	private int duration;

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public Double getRating() {
		return rating;
	}

	public int getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return String.format("%s (%s) = %.2f", getTitle(), getReleaseDate(), getRating());
	}

}
