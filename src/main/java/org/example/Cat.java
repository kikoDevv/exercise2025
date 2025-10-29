package org.example;

import org.example.entities.Food;

import java.util.List;

public record Cat(String name, int age, List<Food> foodList) {
}
