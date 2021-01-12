package com.tecacet.movie.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SimpleMovie implements Movie {

    private String title;
    private int year;
    private LocalDate releaseDate;
    private Double rating;
    private int duration;
    private String plot;
    private String imageUrl;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public String getPlot() {
        return plot;
    }

    @Override
    public Optional<Double> getRating() {
        return Optional.ofNullable(rating);
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public List<? extends Person> getActors() {
        return Collections.emptyList();
    }

    @Override
    public List<? extends Person> getDirectors() {
        return Collections.emptyList();
    }

    @Override
    public List<? extends Genre> getGenres() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return String.format("%s (%s) = %.2f", getTitle(), getReleaseDate(), getRating());
    }
}
