package com.tecacet.movie.jpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.domain.Movie;

@Entity
@Table(name = "movie")
public class EntityMovie implements Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;
	private int year;
	private LocalDate releaseDate;
	private String plot;
	private int duration;
	private Double rating;
	private String imageUrl;

	@ManyToMany
	@JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
	private List<EntityGenre> genres = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "movie_person", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
	private List<EntityPerson> persons = new ArrayList<>();

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
		return duration;
	}

	@Override
	public Optional<Double> getRating() {
		return Optional.ofNullable(rating);
	}

	@Override
	public String getImageUrl() {
		return imageUrl;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void addPerson(EntityPerson person) {
		persons.add(person);
	}

	@Override
	public List<String> getActors() {
		return persons.stream().filter(EntityPerson::isActor).map(EntityPerson::getName).sorted().collect(Collectors.toList());
	}

	@Override
	public List<String> getDirectors() {
		return persons.stream().filter(EntityPerson::isDirector).map(EntityPerson::getName).sorted().collect(Collectors.toList());
	}

	@Override
	public List<? extends Genre> getGenres() {
		return genres;
	}

	public void addGenre(EntityGenre genre) {
		genres.add(genre);
	}
	
	@Override
	public String toString() {
		return getTitle();
	}

}
