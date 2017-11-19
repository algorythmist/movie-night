package com.tecacet.movie.jpa.service;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tecacet.movie.jpa.model.EntityGenre;
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
		Set<EntityGenre> genres = getGenres(movies);
		genreRepository.saveAll(genres);
		Collection<EntityPerson> people = getPeople(movies);
		personRepository.saveAll(people);
		// List<EntityMovie> entities = movies.stream().map(movie -> toMovie(movie,
		// people, genres)).collect(Collectors.toList());
		// movieRepository.saveAll(entities);
	}

	

	private Set<EntityGenre> getGenres(List<JsonMovie> movies) {
		return movies.stream().map(m -> m.getGenres()).flatMap(gl -> gl.stream()).map(g -> new EntityGenre(g.getName()))
				.collect(Collectors.toSet());
	}

	private Collection<EntityPerson> getPeople(List<JsonMovie> movies) {
		Map<String, EntityPerson> people = new HashMap<>();
		for (JsonMovie movie : movies) {
			for (String actor : movie.getActors()) {
				EntityPerson person = people.get(actor);
				if (person == null) {
					person = new EntityPerson(actor, true, false);
					people.put(actor, person);
				}
				person.setActor(true);
			}
			for (String director : movie.getDirectors()) {
				EntityPerson person = people.get(director);
				if (person == null) {
					person = new EntityPerson(director, false, true);
					people.put(director, person);
				}
				person.setDirector(true);
			}
		}
		return people.values();
	}

}
