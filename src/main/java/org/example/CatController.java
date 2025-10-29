package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api")
public class CatController {

    private final CatRepository repository;

    public CatController(CatRepository repository) {
        this.repository = repository;
    }

    @GetMapping("cats")
    public List<Cat> getAll() {
        return repository.findCatsBy().stream()
                .map(cat -> new Cat(cat.getName(), cat.getAge(), cat.getFavorites()))
                .toList();
    }

    @GetMapping("cats/{name}")
    public Cat getByName(@PathVariable String name) {
        return repository.findBy(name)
                .map(cat -> new Cat(cat.getName(), cat.getAge(), cat.getFavorites()))
                .orElseThrow();
    }
}
