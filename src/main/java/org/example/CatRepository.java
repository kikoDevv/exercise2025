package org.example;

import org.example.entities.Cat;
import org.springframework.data.repository.ListCrudRepository;

public interface CatRepository extends ListCrudRepository<Cat, Integer> {
}
