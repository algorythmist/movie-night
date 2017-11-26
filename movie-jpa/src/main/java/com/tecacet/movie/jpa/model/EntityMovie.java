package com.tecacet.movie.jpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.domain.Movie;

@Entity
@Table(name = "movie")
public class EntityMovie implements Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	private String title;
	@Min(1900)
	@Max(2018)
	private int year;
	@Past
	private LocalDate releaseDate;
	private String plot;
	private int duration;
	private Double rating;
	private String imageUrl;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
	private List<EntityGenre> genres = new ArrayList<>();

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
	private List<EntityPerson> actors = new ArrayList<>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "movie_director", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "director_id", referencedColumnName = "id"))
	private List<EntityPerson> directors = new ArrayList<>();

	//for HB
	@SuppressWarnings("unused")
	private EntityMovie() {
		
	}
	
	public EntityMovie(String title) {
		super();
		this.title = title;
		this.year = 2000;
	}
	
	public EntityMovie(Movie movie) {
		this(movie.getTitle());
		this.year = movie.getYear();
		this.duration = movie.getDuration();
		this.releaseDate = movie.getReleaseDate();
		this.plot = movie.getPlot();
		this.rating = movie.getRating().orElse(null);
		this.imageUrl = movie.getImageUrl();
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
	
	public Double getScore() {
		return rating;
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

	public void addActor(EntityPerson person) {
		actors.add(person);
	}
	
	public void addDirector(EntityPerson person) {
		directors.add(person);
	}

	@Override
	public List<EntityPerson> getActors() {
		return actors;
	}

	@Override
	public List<EntityPerson> getDirectors() {
		return directors;
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
		String rating = getRating().isPresent() ? getRating().get().toString() : "No rating";
		return String.format("%s (%d): %s", getTitle(), getYear(), rating);
	}

}
