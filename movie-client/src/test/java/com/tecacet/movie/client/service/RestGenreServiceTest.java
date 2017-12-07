package com.tecacet.movie.client.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.tecacet.movie.client.model.Genre;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockWebServer;

public class RestGenreServiceTest {

	@Test
	public void test() throws IOException {
		MockWebServer server = new MockWebServer();
		
		//server.enqueue(mockResponse);
		server.setDispatcher(new MockRequestDispatcher());
		// Start the server.
		server.start();

		// Ask the server for its URL. You'll need this to make HTTP requests.
		HttpUrl baseUrl = server.url("/");
			
		GenreService genreService = new RestGenreService(baseUrl.toString());

		Genre genre = genreService.findGenreById(1L);
		assertEquals("Action", genre.getName());
		
		List<Genre> allGenres = genreService.getAllGenres();
		assertEquals(5, allGenres.size());
		assertEquals("[99 : Film-Noir, 100 : Action, 101 : Adventure, 102 : Horror, 103 : War]", allGenres.toString());
		
		Genre action = genreService.findGenreByName("action");
		assertNotNull(action);
		
		//shutdown server
		server.shutdown();
		server.close();
	}
	
	@Test
	public void testNotFound() throws IOException {
		MockWebServer server = new MockWebServer();
		server.setDispatcher(new MockRequestDispatcher());
		server.start();
		
		HttpUrl baseUrl = server.url("/");
		GenreService genreService = new RestGenreService(baseUrl.toString());
		try {
			genreService.findGenreById(10L);
			fail();
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
		
		server.shutdown();
		server.close();
	}

}
