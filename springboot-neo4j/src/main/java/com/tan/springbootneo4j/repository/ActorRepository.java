package com.tan.springbootneo4j.repository;

import com.tan.springbootneo4j.entity.Actor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends Neo4jRepository<Actor,Long> {
    Actor findByName(@Param("name") String name);
}
