package com.tecacet.movie.client.service;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

public class MockRequestDispatcher extends Dispatcher {

	private static final String ALL_GENRES = 
			"[\r\n" 
			+ "    {\r\n" 
			+ "        \"id\": 99,\r\n"
			+ "        \"name\": \"Film-Noir\"\r\n" + "    },\r\n" 
			+ "    {\r\n" + "        \"id\": 100,\r\n"
			+ "        \"name\": \"Action\"\r\n" 
			+ "    },\r\n" 
			+ "    {\r\n" 
			+ "        \"id\": 101,\r\n"
			+ "        \"name\": \"Adventure\"\r\n" 
			+ "    },\r\n" 
			+ "    {\r\n" + "        \"id\": 102,\r\n"
			+ "        \"name\": \"Horror\"\r\n" + "    },\r\n" 
			+ "    {\r\n" + "        \"id\": 103,\r\n"
			+ "        \"name\": \"War\"\r\n" 
			+ "    }\r\n" 
			+ "]";

	@Override
	public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
		 if (request.getPath().equals("/genres/1")){
			 return new MockResponse().setHeader("Content-Type", "application/json")
						.setBody("{\r\n" + 
								"    \"id\": 100,\r\n" + 
								"    \"name\": \"Action\"\r\n" + 
								"}");
		 } else if (request.getPath().equals("/genres/list")) {
			 return new MockResponse().setHeader("Content-Type", "application/json")
						.setBody(ALL_GENRES);
		 } else if (request.getPath().startsWith("/genres/byname/")) {
				 return new MockResponse().setHeader("Content-Type", "application/json")
							.setBody("{\r\n" + 
									"    \"id\": 100,\r\n" + 
									"    \"name\": \"Action\"\r\n" + 
									"}");
		}
		return new MockResponse().setResponseCode(404);
	}

}
