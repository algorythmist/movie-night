package com.tecacet.movie.jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecacet.movie.domain.Director;
import com.tecacet.movie.service.DirectorRatingService;

@Service
public class NativeDirectorRatingService implements DirectorRatingService {

	private final EntityManager entityManager;
	
	String queryStr = "select new com.tecacet.movie.jpa.service.ImmutableDirector(d.name, AVG(m.rating), COUNT(m))" +
			"from MovieDirector md inner join md.director d " +
			"inner join md.movie m " +
			"where m.rating is not null " +
			"group by d.name " +
			"having COUNT(m) > 2 " +
			"order by AVG(m.rating) desc,  COUNT(m) desc";
			
	
	@Autowired
	public NativeDirectorRatingService(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<? extends Director> findTopDirectors(int top) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery(queryStr, Director.class).setMaxResults(top);
		return query.getResultList();
	}

}
