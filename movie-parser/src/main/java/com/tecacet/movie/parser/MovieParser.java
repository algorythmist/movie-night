package com.tecacet.movie.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieParser {

	public List<JsonMovie> parse(String filename) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String array = new String(Files.readAllBytes(Paths.get(filename)));
		return objectMapper.readValue(array, new TypeReference<List<JsonMovie>>(){});
	}
}
