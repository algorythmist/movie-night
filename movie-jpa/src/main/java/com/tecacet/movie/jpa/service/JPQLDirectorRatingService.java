package com.tecacet.movie.jpa.service;

import com.tecacet.movie.domain.Director;
import com.tecacet.movie.service.DirectorRatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class JPQLDirectorRatingService implements DirectorRatingService {

    private final EntityManager entityManager;

    private final String queryStr = "select new com.tecacet.movie.jpa.service.ImmutableDirector(d.name, AVG(m.rating), COUNT(m))" +
            "from MovieDirector md inner join md.director d " +
            "inner join md.movie m " +
            "where m.rating is not null " +
            "group by d.name " +
            "having COUNT(m) > 2 " +
            "order by AVG(m.rating) desc,  COUNT(m) desc";


    @Autowired
    public JPQLDirectorRatingService(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends Director> findTopDirectors(int top) {
        Query query = entityManager.createQuery(queryStr, Director.class).setMaxResults(top);
        return query.getResultList();
    }
}
