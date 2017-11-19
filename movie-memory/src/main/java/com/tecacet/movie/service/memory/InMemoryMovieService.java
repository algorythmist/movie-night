package com.tecacet.movie.service.memory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.domain.Person;
import com.tecacet.movie.parser.MovieParser;
import com.tecacet.movie.service.MovieService;

/**
 * In Memory implementation of the MovieService
 * 
 * @author dimitri
 *
 */
public class InMemoryMovieService implements MovieService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final List<Movie> movies = new ArrayList<>();
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
	
	public InMemoryMovieService(MovieParser movieParser) throws IOException {
		this(movieParser.parse("moviedata.json"));
	}

	public InMemoryMovieService(List<? extends Movie> allMovies) {
		logger.info("Registering {} movies with the service", allMovies.size());
		for (Movie movie : allMovies) {
			movies.add(movie);
			registerActors(movie);
			registerDirectors(movie);
			registerGenres(movie);
		}
	}

	private void registerGenres(Movie movie) {
		for (Genre g : movie.getGenres()) {
			EnrichedGenre genre = findGenre(g.getName());
			genre.addMovie(movie);
		}
	}

	private void registerDirectors(Movie movie) {
		for (Person p : movie.getDirectors()) {
			EnrichedPerson person = findPerson(p.getName());
			person.addMovieDirected(movie);
			person.setDirector(true);
		}
	}

	private void registerActors(Movie movie) {
		for (Person p : movie.getActors()) {
			EnrichedPerson person = findPerson(p.getName());
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
		return new ArrayList<>(movies);
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

	@Override
	public List<Movie> findByTitle(String name) {
		return movies.stream().filter(m -> name.equals(m.getTitle())).collect(Collectors.toList());
	}

}
