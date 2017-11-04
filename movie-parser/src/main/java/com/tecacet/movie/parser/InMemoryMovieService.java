package com.tecacet.movie.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.tecacet.movies.domain.Person;
import com.tecacet.movies.domain.Genre;
import com.tecacet.movies.domain.Movie;
import com.tecacet.movies.service.MovieService;

public class InMemoryMovieService implements MovieService {

	private final Map<String, Movie> moviesByTitle = new TreeMap<>();
	private final Map<String, EnrichedPerson> personsByName = new TreeMap<>();
	private final Map<String, EnrichedGenre> genresByName = new TreeMap<>();

	private class EnrichedGenre implements Genre {
		private final String name;
		private final List<Movie> movies = new ArrayList<>();

		public EnrichedGenre(String name) {
			super();
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}

		public List<Movie> getMovies() {
			return movies;
		}

		public void addMovie(Movie movie) {
			movies.add(movie);
		}
		
		@Override
		public String toString() {
			return name;
		}

	}

	private class EnrichedPerson implements Person {
		private final String name;

		private boolean isActor = false;
		private boolean isDirector = false;
		private final List<Movie> moviesActed = new ArrayList<>();
		private final List<Movie> moviesDirected = new ArrayList<>();

		public EnrichedPerson(String name) {
			super();
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
		
		@Override
		public String toString() {
			return name;
		}

		public boolean isActor() {
			return isActor;
		}

		public void setActor(boolean isActor) {
			this.isActor = isActor;
		}

		public boolean isDirector() {
			return isDirector;
		}

		public void setDirector(boolean isDirector) {
			this.isDirector = isDirector;
		}

		public void addMovieActed(Movie movie) {
			moviesActed.add(movie);
		}

		public void addMovieDirected(Movie movie) {
			moviesDirected.add(movie);
		}

		public List<Movie> getMoviesActed() {
			return moviesActed;
		}

		public List<Movie> getMoviesDirected() {
			return moviesDirected;
		}

	}

	public InMemoryMovieService(List<? extends Movie> allMovies) {
		for (Movie movie : allMovies) {
			moviesByTitle.put(movie.getTitle(), movie);
			registerActors(movie);
			registerDirectors(movie);
			registerGenres(movie);
		}
	}

	private void registerGenres(Movie movie) {
		for (String name : movie.getGenres()) {
			EnrichedGenre genre = findGenre(name);
			genre.addMovie(movie);
		}
	}

	private void registerDirectors(Movie movie) {
		for (String name : movie.getDirectors()) {
			EnrichedPerson person = findPerson(name);
			person.addMovieDirected(movie);
			person.setDirector(true);
		}
	}

	private void registerActors(Movie movie) {
		for (String name : movie.getActors()) {
			EnrichedPerson person = findPerson(name);
			person.addMovieActed(movie);
			person.setActor(true);
		}
	}

	private EnrichedGenre findGenre(String name) {
		EnrichedGenre genre = genresByName.get(name);
		if (genre == null) {
			genre = new EnrichedGenre(name);
			genresByName.put(name, genre);
		}
		return genre;
	}

	private EnrichedPerson findPerson(String name) {
		EnrichedPerson person = personsByName.get(name);
		if (person == null) {
			person = new EnrichedPerson(name);
			personsByName.put(name, person);
		}
		return person;
	}

	@Override
	public List<Movie> findMoviesWithActor(String actorName) {
		EnrichedPerson person = personsByName.get(actorName);
		if (person == null) {
			return Collections.emptyList();
		}
		return person.getMoviesActed();
	}

	@Override
	public List<Movie> findMoviesWithDirector(String directorName) {
		EnrichedPerson person = personsByName.get(directorName);
		if (person == null) {
			return Collections.emptyList();
		}
		return person.getMoviesDirected();
	}

	@Override
	public List<Movie> getAllMovies() {
		return new ArrayList<>(moviesByTitle.values());
	}

	@Override
	public List<Person> getAllActors() {
		return personsByName.values().stream().filter(EnrichedPerson::isActor).collect(Collectors.toList());
	}

	@Override
	public List<Person> getAllDirectors() {
		return personsByName.values().stream().filter(EnrichedPerson::isDirector).collect(Collectors.toList());
	}

	@Override
	public List<Genre> getAllGenres() {
		return new ArrayList<>(genresByName.values());
	}

	@Override
	public List<Movie> findMoviesInGenre(String genreName) {
		EnrichedGenre genre = genresByName.get(genreName);
		if (genre == null) {
			return Collections.emptyList();
		}
		return genre.getMovies();
	}

}
