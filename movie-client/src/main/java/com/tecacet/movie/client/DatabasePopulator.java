package com.tecacet.movie.client;

import org.springframework.web.client.RestTemplate;

public class DatabasePopulator {

    private static final String POPULATE_URL = "http://localhost:8080/admin/populate";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(POPULATE_URL, null);
    }
}
