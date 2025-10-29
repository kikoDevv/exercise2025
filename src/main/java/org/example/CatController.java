package org.example;

import org.springframework.web.bind.annotation.GetMapping;
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
        return repository.findAll().stream()
                .map(cat -> new Cat(cat.getName(), cat.getAge()))
                .toList();
    }
}
