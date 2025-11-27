package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CatRepository repository;

    public HomeController(CatRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home(Model model) {
        var cats = repository.findAll();
        model.addAttribute("cats", cats);
        return "index";
    }
}
