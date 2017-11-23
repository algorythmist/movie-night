package com.tecacet.movie.jpa.model;


import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class MovieValidationTest {

	@Test
	public void test() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
	
		EntityMovie invalidMovie = new EntityMovie(""); 
		invalidMovie.setYear(1204); //sack of Constantinople by the Crusaders
		invalidMovie.setReleaseDate(LocalDate.of(2020, 3, 12));
		invalidMovie.setDuration(10);
		
		Set<ConstraintViolation<EntityMovie>> violations = validator.validate(invalidMovie);
		
		Map<String, ConstraintViolation<EntityMovie>> byProperty =
				violations.stream().collect(Collectors.toMap(c -> c.getPropertyPath().toString(),c-> c));
		ConstraintViolation<EntityMovie> violation = byProperty.get("title");
		assertEquals("must not be empty", violation.getMessage());
		violation = byProperty.get("year");
		assertEquals("must be greater than or equal to 1900", violation.getMessage());
		violation = byProperty.get("releaseDate");
		assertEquals("must be a past date", violation.getMessage());
	}

}
