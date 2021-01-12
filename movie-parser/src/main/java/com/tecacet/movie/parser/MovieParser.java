package com.tecacet.movie.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public List<JsonMovie> parse(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = ClassLoader.getSystemResourceAsStream(filename);
        return objectMapper.readValue(is, new TypeReference<List<JsonMovie>>() {
        });
    }
}
