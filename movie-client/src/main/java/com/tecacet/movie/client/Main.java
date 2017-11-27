package com.tecacet.movie.client;

import com.tecacet.movie.client.model.Genre;
import com.tecacet.movie.client.service.GenreService;
import com.tecacet.movie.client.service.RestGenreService;

public class Main {
		
	public static void main(String[] args) {
		GenreService genreService = new RestGenreService();
		System.out.println(genreService.getAllGenres());
		
		Genre genre = genreService.create("Test");
		System.out.println(genre);
		genreService.delete(genre.getId());
	}
}
