package com.tecacet.movie.controller;

import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.entity.EntityGenre;
import com.tecacet.movie.repository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/genres")
public class GenreController {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository) {
        super();
        this.genreRepository = genreRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<? extends Genre> getAllMovies() {
        return genreRepository.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Genre create(@RequestBody String name) {
        EntityGenre genre = new EntityGenre(name);
        return genreRepository.save(genre);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Genre findGenreById(@PathVariable long id) throws ResourceNotFoundException {
        return genreRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(value = "/byname/{name}", method = RequestMethod.GET)
    public Genre findGenreByName(@PathVariable String name) throws ResourceNotFoundException {
        return genreRepository.findByNameIgnoreCase(name).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        genreRepository.deleteById(id);
    }
}
