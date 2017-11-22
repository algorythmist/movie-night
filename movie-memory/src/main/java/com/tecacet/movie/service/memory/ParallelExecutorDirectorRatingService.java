package com.tecacet.movie.service.memory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tecacet.movie.domain.Director;
import com.tecacet.movie.domain.Movie;
import com.tecacet.movie.domain.Person;
import com.tecacet.movie.service.DirectorRatingService;
import com.tecacet.movie.service.MovieService;

public class ParallelExecutorDirectorRatingService implements DirectorRatingService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final MovieService movieService;

	public ParallelExecutorDirectorRatingService(MovieService movieService) {
		this.movieService = movieService;
	}

	@Override
	public List<? extends Director> findTopDirectors(int top) {
		List<? extends Person> allDirectors = movieService.getAllDirectors();
		logger.info("Comparing {} directors", allDirectors.size());
		Comparator<ImmutableDirector> ratingComparator = Comparator.comparing(ImmutableDirector::getRating).reversed();
		Comparator<Director> movieComparator = Comparator.comparing(d -> d.getMovies());
		Queue<ImmutableDirector> priorityQueue = new PriorityBlockingQueue<>(movieService.getAllDirectors().size(),
				ratingComparator.thenComparing(movieComparator.reversed()));

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		allDirectors.forEach(
				person -> executorService.submit(() -> 
					processDirector(person).ifPresent(d -> priorityQueue.add(d))));
		executorService.shutdown();
		try {
			executorService.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return toList(priorityQueue, top);
	}

	private Optional<ImmutableDirector> processDirector(Person person) {
		List<? extends Movie> movies = movieService.findMoviesWithDirector(person.getName());
		if (movies.size() < 3) {
			return Optional.empty();
		}
		OptionalDouble opt = getAverageRating(movies);
		if (!opt.isPresent()) {
			return Optional.empty();
		}
		Set<String> genres = getGenres(movies);
		return Optional.of(new ImmutableDirector(person.getName(), opt.getAsDouble(), movies.size(), genres));
	}

	private List<ImmutableDirector> toList(Queue<ImmutableDirector> directors, int size) {
		int range = directors.size() < size ? directors.size() : size;
		return IntStream.range(0, range).mapToObj(i -> directors.remove()).collect(Collectors.toList());
	}

	private OptionalDouble getAverageRating(List<? extends Movie> movies) {
		return movies.stream().filter(m -> m.getRating().isPresent()).mapToDouble(m -> m.getRating().get()).average();
	}

	private Set<String> getGenres(List<? extends Movie> movies) {
		return movies.stream().map(m -> m.getGenres()).flatMap(gl -> gl.stream()).map(g -> g.getName())
				.collect(Collectors.toSet());
	}

}
