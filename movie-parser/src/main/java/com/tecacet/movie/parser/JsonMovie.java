package com.tecacet.movie.parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tecacet.movies.domain.Movie;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonMovie implements Movie {

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Info {

		@JsonDeserialize(using = LocalDateDeserializer.class)
		private LocalDate release_date;
		private int running_time_secs;
		private double rating;
		private String image_url;
		private String plot;
		private List<String> directors = new ArrayList<>();
		private List<String> genres = new ArrayList<>();
		private List<String> actors = new ArrayList<>();

		public double getRating() {
			return rating;
		}

		public String getPlot() {
			return plot;
		}

		public List<String> getDirectors() {
			return directors;
		}

		public List<String> getActors() {
			return actors;
		}

		public List<String> getGenres() {
			return genres;
		}

		public String getImage_url() {
			return image_url;
		}

		public int getRunning_time_secs() {
			return running_time_secs;
		}

		public LocalDate getRelease_date() {
			return release_date;
		}

	}

	private String title;
	private int year;
	@JsonDeserialize
	private Info info;

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public int getYear() {
		return year;
	}

	@Override
	public LocalDate getReleaseDate() {
		return info.getRelease_date();
	}

	@Override
	public String getPlot() {
		return info.getPlot();
	}

	@Override
	public int getDuration() {
		return info.getRunning_time_secs() / 60;
	}

	@Override
	public double getRating() {
		return info.getRating();
	}

	@Override
	public String getImageUrl() {
		return info.getImage_url();
	}

	@Override
	public List<String> getDirectors() {
		return info.getDirectors();
	}
	
	@Override
	public List<String> getActors() {
		return info.getActors();
	}

	@Override
	public List<String> getGenres() {
		return info.getGenres();
	}

	@Override
	public String toString() {
		return String.format("%s (%d): %f", getTitle(), getYear(), getRating());
	}

}
