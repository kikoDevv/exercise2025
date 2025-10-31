package org.example;

import jakarta.persistence.NamedEntityGraph;
import org.example.entities.Cat;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CatRepository extends ListCrudRepository<Cat, Integer> {
    Optional<Cat> findCatByName(String name);

    //JPQL-query
    @Query("""
        select cat.id, upper(cat.name), cat.age, cat.createdAt from Cat cat where cat.name = :name
    """)
    Optional<Cat> findBy(@Param("name") String name);

    @Query("""
        from Cat cat join fetch cat.favorites f
        """)
    List<Cat> findCatsAndFood();

    @EntityGraph("Cat.favorites")
    List<Cat> findCatsBy();
}
