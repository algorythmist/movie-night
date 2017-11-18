package com.tecacet.movie.jpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecacet.movie.jpa.config.PersistanceConfiguration;
import com.tecacet.movie.jpa.model.EntityPerson;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersistanceConfiguration.class })
@Transactional
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void test() {
		EntityPerson person1 = new EntityPerson("Tom", false, true);
		EntityPerson person2 = new EntityPerson("Dale", true, true);

		personRepository.save(person1);
		assertTrue(person1.getId() > 0);
		personRepository.save(person2);

		Optional<EntityPerson> found = personRepository.findById(person1.getId());
		assertEquals("Tom", found.get().getName());

		List<EntityPerson> allPeople = personRepository.findAll();
		assertEquals(2, allPeople.size());
		
		List<EntityPerson> actors = personRepository.findAllActors();
		System.out.println(actors); //TODO
		
		List<EntityPerson> directors = personRepository.findAllDirectors();
		System.out.println(directors); //TODO

		personRepository.delete(person1);
		allPeople = personRepository.findAll();
		assertEquals(1, allPeople.size());
	}

}
