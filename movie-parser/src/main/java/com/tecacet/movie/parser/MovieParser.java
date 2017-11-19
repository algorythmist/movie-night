package com.tecacet.movie.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Parse a JSON file containing movie information
 * @see JsonMovie
 * 
 * @author dimitri
 *
 */
public class MovieParser {

	public List<JsonMovie> parse(String filename) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		InputStream is = ClassLoader.getSystemResourceAsStream(filename);
		return objectMapper.readValue(is, new TypeReference<List<JsonMovie>>(){});
	}
}
