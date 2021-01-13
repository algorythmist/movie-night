package com.tecacet.movie.controller;


import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.domain.SimpleMovie;
import com.tecacet.movie.entity.EntityMovie;
import com.tecacet.movie.repository.MovieRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        super();
        this.movieRepository = movieRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<? extends Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Movie findMovieById(@PathVariable long id) {
        Optional<? extends Movie> optional = movieRepository.findById(id);
        return optional.orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Movie modify(@PathVariable long id, @RequestBody SimpleMovie movie) throws ResourceNotFoundException {
        EntityMovie entityMovie = movieRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        // copy attributes
        BeanUtils.copyProperties(movie, entityMovie);
        movieRepository.save(entityMovie);
        return entityMovie;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Movie create(@RequestBody SimpleMovie movie) {
        EntityMovie entityMovie = new EntityMovie(movie);
        movieRepository.save(entityMovie);
        return entityMovie;
    }

}
