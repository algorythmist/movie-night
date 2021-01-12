package com.tecacet.movie.jpa.service;

import com.tecacet.movie.domain.Director;
import com.tecacet.movie.service.DirectorRatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class NativeDirectorRatingService implements DirectorRatingService {

    private final EntityManager entityManager;

    private final String queryStr = "select p.name as name, count(m.id) as ct, avg(m.rating) as rt "
            + "from movie_director md " + "inner join person p on p.id = md.director_id "
            + "inner join movie m on m.id = md.movie_id " + "where m.rating is not null " + "group by p.name "
            + "having count(m.id) > 2 " + "order by rt desc, ct desc";

    @Autowired
    public NativeDirectorRatingService(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends Director> findTopDirectors(int top) {
        Query query = entityManager.createNativeQuery(queryStr).setMaxResults(top);
        return (List<? extends Director>) query.getResultList().stream().map(result -> toDirector((Object[]) result))
                .collect(Collectors.toList());
    }

    private Director toDirector(Object[] result) {
        return new ImmutableDirector(result[0].toString(), ((Number) result[2]).doubleValue(),
                ((Number) result[1]).longValue());
    }
}
