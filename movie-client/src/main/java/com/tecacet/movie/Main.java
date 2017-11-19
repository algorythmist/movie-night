package com.tecacet.movie;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Main {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://localhost:8080/";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		System.out.println(response.getBody());
	}
}
