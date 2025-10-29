package org.example;

import org.example.entities.Cat;
import org.example.entities.Food;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
public class Exercise2025Application {
    void main(String[] args) {
        SpringApplication.run(Exercise2025Application.class, args);
    }

    @Bean
    @Profile("dev")
    ApplicationRunner initializeDatabaseInDev(CatRepository repository, FoodRepository foodRepository) {
        return args -> {
            if (repository.count() == 0) {
                var food1 = new Food("Salmon");
                var food2 = new Food("Cream");
                var food3 = new Food("Meat");
                var food4 = new Food("Milk");
                foodRepository.saveAll(List.of(food1, food2, food3, food4));

                repository.saveAll(List.of(
                        new org.example.entities.Cat("Sigge", 13, List.of(food1, food2)),
                        new Cat("Maja", 3, List.of(food3, food4))
                ));
            }
        };
    }


}
