package com.tecacet.movie.client.model;

public class Genre {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%d : %s", id, name);
    }
}
