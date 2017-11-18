package com.tecacet.movie.parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.tecacet.movie.domain.Movie;

public class MovieParserTest {

	@Test
	public void parse() throws IOException {
		MovieParser movieParser = new MovieParser();
		List<JsonMovie> movies = movieParser.parse("moviedata.json");
		assertEquals(4609, movies.size());
		Movie movie = movies.get(0);
		assertEquals("Rush (2013): 8.3", movie.toString());
		assertEquals("[Ron Howard]", movie.getDirectors().toString());
		assertEquals("[Action, Biography, Drama, Sport]", movie.getGenres().toString());
		assertEquals(
				"http://ia.media-imdb.com/images/M/MV5BMTQyMDE0MTY0OV5BMl5BanBnXkFtZTcwMjI2OTI0OQ@@._V1_SX400_.jpg",
				movie.getImageUrl());
		assertEquals("[Daniel Bruhl, Chris Hemsworth, Olivia Wilde]", movie.getActors().toString());
		assertEquals(123, movie.getDuration());
		assertEquals("2013-09-02", movie.getReleaseDate().toString());
		assertEquals(
				"A re-creation of the merciless 1970s rivalry between Formula One rivals James Hunt and Niki Lauda.",
				movie.getPlot());
	}

}
