package com.tecacet.movie.parser;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Deserialize LocalDate using the format in the movie file
 * 
 * @author dimitri
 *
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Override
	public LocalDate deserialize(JsonParser jsonParser, DeserializationContext ctx)
			throws IOException, JsonProcessingException {
		
		String text = jsonParser.getText();
		if (text == null || text.isEmpty()) {
			return null;
		}
		int i = text.indexOf("T");
		if (i < 0) {
			return null;
		}
		return LocalDate.parse(text.substring(0, i), formatter);
	}

}
