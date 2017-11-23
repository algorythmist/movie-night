package com.tecacet.movie.jpa.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.domain.Person;
import com.tecacet.movie.jpa.model.EntityGenre;
import com.tecacet.movie.jpa.model.EntityMovie;
import com.tecacet.movie.jpa.model.EntityPerson;
import com.tecacet.movie.jpa.repository.GenreRepository;
import com.tecacet.movie.jpa.repository.MovieRepository;
import com.tecacet.movie.jpa.repository.PersonRepository;
import com.tecacet.movie.parser.JsonMovie;
import com.tecacet.movie.parser.MovieParser;

@Component
public class DatabasePopulator {

	private final MovieParser movieParser = new MovieParser();

	private final MovieRepository movieRepository;
	private final GenreRepository genreRepository;
	private final PersonRepository personRepository;

	@Autowired
	public DatabasePopulator(MovieRepository movieRepository, GenreRepository genreRepository,
			PersonRepository personRepository) {
		super();
		this.movieRepository = movieRepository;
		this.genreRepository = genreRepository;
		this.personRepository = personRepository;
	}

	public void loadMovies() throws IOException {
		List<JsonMovie> movies = movieParser.parse("moviedata.json");
		Map<String, EntityGenre> genres = getGenres(movies);
		genreRepository.saveAll(genres.values());
		Map<String, EntityPerson> people = getPeople(movies);
		personRepository.saveAll(people.values());
		List<EntityMovie> entities = movies.stream().map(movie -> toEntity(movie, genres, people))
				.collect(Collectors.toList());
		movieRepository.saveAll(entities);
	}
	
	public void deleteData() {
		movieRepository.deleteAll();
		personRepository.deleteAll();
		genreRepository.deleteAll();
	}

	private EntityMovie toEntity(JsonMovie movie, Map<String, EntityGenre> genres, Map<String, EntityPerson> people) {
		EntityMovie entityMovie = new EntityMovie(movie);
		for (Genre genre : movie.getGenres()) {
			EntityGenre entityGenre = genres.get(genre.getName());
			if (entityGenre != null) {
				entityMovie.addGenre(entityGenre);
			}
		}
		for (Person actor : movie.getActors()) {
			EntityPerson person = people.get(actor.getName());
			if (person != null) {
				entityMovie.addActor(person);
			}
		}
		for (Person director : movie.getDirectors()) {
			EntityPerson person = people.get(director.getName());
			if (person != null) {
				entityMovie.addDirector(person);
			}
		}
		return entityMovie;
	}

	private Map<String, EntityGenre> getGenres(List<JsonMovie> movies) {
		return movies.stream().map(m -> m.getGenres()).flatMap(gl -> gl.stream()).map(g -> g.getName()).distinct()
				.map(g -> new EntityGenre(g)).collect(Collectors.toMap(e -> e.getName(), e -> e));
	}

	private Map<String, EntityPerson> getPeople(List<JsonMovie> movies) {
		Map<String, EntityPerson> people = new HashMap<>();
		for (JsonMovie movie : movies) {
			for (Person actor : movie.getActors()) {
				EntityPerson person = people.get(actor.getName());
				if (person == null) {
					person = new EntityPerson(actor.getName());
					people.put(actor.getName(), person);
				}

			}
			for (Person director : movie.getDirectors()) {
				EntityPerson person = people.get(director.getName());
				if (person == null) {
					person = new EntityPerson(director.getName());
					people.put(director.getName(), person);
				}
			}
		}
		return people;
	}

}
