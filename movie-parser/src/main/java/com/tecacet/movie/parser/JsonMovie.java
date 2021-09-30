package com.tecacet.movie.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.domain.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Movie implementation with Jackson annotations for mapping to JSON
 *
 * @author dimitri
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonMovie implements Movie {

    private String title;
    private int year;
    @JsonDeserialize
    private Info info;

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
        return info.getRelease_date();
    }

    @Override
    public String getPlot() {
        return info.getPlot();
    }

    @Override
    public int getDuration() {
        return info.getRunning_time_secs() / 60;
    }

    @Override
    public Optional<Double> getRating() {
        return Optional.ofNullable(info.getRating());
    }

    @Override
    public String getImageUrl() {
        return info.getImage_url();
    }

    @Override
    public List<? extends Person> getDirectors() {
        return info.getDirectors().stream().map(SimplePerson::new).collect(Collectors.toList());
    }

    @Override
    public List<? extends Person> getActors() {
        return info.getActors().stream().map(SimplePerson::new).collect(Collectors.toList());
    }

    @Override
    public List<? extends Genre> getGenres() {
        return info.getGenres().stream().map(SimpleGenre::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String rating = getRating().isPresent() ? getRating().get().toString() : "No rating";
        return String.format("%s (%d): %s", getTitle(), getYear(), rating);
    }


    private class SimpleGenre implements Genre {
        private final String name;

        public SimpleGenre(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }


    private class SimplePerson implements Person {
        private final String name;

        public SimplePerson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Info {

        private LocalDate release_date;
        private int running_time_secs;
        private Double rating;
        private String image_url;
        private String plot;
        private final List<String> directors = new ArrayList<>();
        private final List<String> genres = new ArrayList<>();
        private final List<String> actors = new ArrayList<>();

        public Double getRating() {
            return rating;
        }

        public String getPlot() {
            return plot;
        }

        public List<String> getDirectors() {
            return directors;
        }

        public List<String> getActors() {
            return actors;
        }

        public List<String> getGenres() {
            return genres;
        }

        public String getImage_url() {
            return image_url;
        }

        public int getRunning_time_secs() {
            return running_time_secs;
        }

        public LocalDate getRelease_date() {
            return release_date;
        }

    }

}
