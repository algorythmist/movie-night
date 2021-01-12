package com.tecacet.movie.service.memory;

import com.tecacet.movie.domain.Director;
import com.tecacet.movie.domain.Genre;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.domain.Person;
import com.tecacet.movie.service.DirectorRatingService;
import com.tecacet.movie.service.MovieService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementation of the service that compares every director
 *
 * @author dimitri
 */
public class ExhaustiveDirectorRatingService implements DirectorRatingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MovieService movieService;

    public ExhaustiveDirectorRatingService(MovieService movieService) {
        this.movieService = movieService;
    }

    public List<ImmutableDirector> findTopDirectors(int top) {
        Comparator<ImmutableDirector> ratingComparator = Comparator.comparing(ImmutableDirector::getRating).reversed();
        Comparator<Director> movieComparator = Comparator.comparing(Director::getMovies);
        Queue<ImmutableDirector> priorityQueue = new PriorityQueue<>(ratingComparator.thenComparing(movieComparator.reversed()));
        List<? extends Person> allDirectors = movieService.getAllDirectors();
        logger.info("Comparing {} directors", allDirectors.size());
        for (Person person : allDirectors) {
            List<? extends Movie> movies = movieService.findMoviesWithDirector(person.getName());
            if (movies.size() < 3) {
                continue;
            }
            OptionalDouble opt = getAverageRating(movies);
            if (!opt.isPresent()) {
                continue;
            }
            Set<String> genres = getGenres(movies);
            ImmutableDirector director = new ImmutableDirector(person.getName(), opt.getAsDouble(), movies.size(), genres);
            priorityQueue.add(director);
        }
        return toList(priorityQueue, top);
    }

    private List<ImmutableDirector> toList(Queue<ImmutableDirector> directors, int size) {
        int range = Math.min(directors.size(), size);
        return IntStream.range(0, range).mapToObj(i -> directors.remove()).collect(Collectors.toList());
    }

    private OptionalDouble getAverageRating(List<? extends Movie> movies) {
        return movies.stream().filter(m -> m.getRating().isPresent()).mapToDouble(m -> m.getRating().get()).average();
    }

    private Set<String> getGenres(List<? extends Movie> movies) {
        return movies.stream().map(Movie::getGenres)
                .flatMap(Collection::stream)
                .map(Genre::getName)
                .collect(Collectors.toSet());
    }

}
