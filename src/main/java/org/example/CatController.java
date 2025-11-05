package org.example;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CatController {

    private final CatRepository repository;

    public CatController(CatRepository repository) {
        this.repository = repository;
    }

    @GetMapping("cats")
    @PreAuthorize("hasRole('API')")  //Spring Expression Language (SpEL)
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
