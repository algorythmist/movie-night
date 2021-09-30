package com.tecacet.movie.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Parse a JSON file containing movie information
 *
 * @author dimitri
 * @see JsonMovie
 */
public class MovieParser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public MovieParser() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public List<JsonMovie> parse(String filename) throws IOException {
        InputStream is = ClassLoader.getSystemResourceAsStream(filename);
        return objectMapper.readValue(is, new TypeReference<>() {
        });
    }
}
