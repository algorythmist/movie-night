package com.tecacet.movie.service;

import com.tecacet.movie.domain.Director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class NativeDirectorRatingService implements DirectorRatingService {

    private static final String QUERY_DIRECTORS = "select p.name as name, count(m.id) as ct, avg(m.rating) as rt "
            + "from movie_director md " + "inner join person p on p.id = md.director_id "
            + "inner join movie m on m.id = md.movie_id " + "where m.rating is not null " + "group by p.name "
            + "having count(m.id) > 2 " + "order by rt desc, ct desc";

    private final EntityManager entityManager;

    @Autowired
    public NativeDirectorRatingService(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @Override
    public List<? extends Director> findTopDirectors(int top) {
        Query query = entityManager.createNativeQuery(QUERY_DIRECTORS).setMaxResults(top);
        return (List<? extends Director>) query.getResultList().stream().map(result -> toDirector((Object[]) result))
                .collect(Collectors.toList());
    }

    private Director toDirector(Object[] result) {
        return new ImmutableDirector(result[0].toString(), ((Number) result[2]).doubleValue(),
                ((Number) result[1]).longValue());
    }
}
