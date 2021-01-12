package com.tecacet.movie.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tecacet.movie.entity.EntityPerson;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @AfterEach
    public void cleanUp() {
        personRepository.deleteAll();
    }

    @Test
    public void test() {
        EntityPerson person1 = new EntityPerson("Tom");
        EntityPerson person2 = new EntityPerson("Dale");

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
