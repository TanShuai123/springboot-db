package com.tan.springbootneo4j;

import com.tan.springbootneo4j.entity.Actor;
import com.tan.springbootneo4j.entity.Movie;
import com.tan.springbootneo4j.entity.Role;
import com.tan.springbootneo4j.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Neo4jTest {

    @Autowired
    private MovieRepository movieRepository;

    @Before
    public void initData() {
        movieRepository.deleteAll();
        Movie matrix1 = new Movie();
        matrix1.setTitle("The Matrix");
        matrix1.setYear("1999-03-31");

        Movie matrix2 = new Movie();
        matrix2.setTitle("The Matrix Reloaded");
        matrix2.setYear("2003-05-07");

        Movie matrix3 = new Movie();
        matrix3.setTitle("The Matrix Revolutions");
        matrix3.setYear("2003-10-27");

        Actor keanu = new Actor();
        keanu.setName("Keanu Reeves");

        Actor laurence = new Actor();
        laurence.setName("Laurence Fishburne");

        Actor carrieanne = new Actor();
        carrieanne.setName("Carrie-Anne Moss");

        matrix1.addRole(keanu,  "Neo");
        matrix1.addRole(laurence, "Morpheus");
        matrix1.addRole(carrieanne,  "Trinity");
        movieRepository.save(matrix1);
        Assert.assertNotNull(matrix1.getId());

        matrix2.addRole(keanu, "Neo");
        matrix2.addRole(laurence, "Morpheus");
        matrix2.addRole(carrieanne,  "Trinity");
        movieRepository.save(matrix2);
        Assert.assertNotNull(matrix2.getId());

        matrix3.addRole(keanu, "Neo");
        matrix3.addRole(laurence, "Morpheus");
        matrix3.addRole(carrieanne, "Trinity");
        movieRepository.save(matrix3);
        Assert.assertNotNull(matrix3.getId());
    }

    @Test
    public void get() {
        Movie movie = movieRepository.findByTitle("The Matrix");
        Assert.assertNotNull(movie);
        log.info("===movie=== {},{}", movie.getTitle(), movie.getYear());
        for (Role role : movie.getRoleList()) {
            log.info("actor:{},role:{}", role.getActor().getName(), role.getRoleName());
        }
    }
}
