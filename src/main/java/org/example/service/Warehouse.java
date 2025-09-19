package org.example.service;

import org.example.entities.Category;
import org.example.entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private final Map<String, Product> products;

    public Warehouse() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (product.name() == null || product.name().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        products.put(product.id(), product);
    }

    public void updateProduct(String id, String name, Category category, int rating) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        Product existingProduct = products.get(id);
        if (existingProduct == null) {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }

        Product updatedProduct = existingProduct.withUpdatedFields(name, category, rating);
        products.put(id, updatedProduct);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> getProductById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> getProductsByCategorySorted(Category category) {
        if (category == null) {
            return new ArrayList<>();
        }

        return products.values().stream()
                .filter(product -> product.category() == category)
                .sorted(Comparator.comparing(Product::name))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsCreatedAfter(LocalDate date) {
        if (date == null) {
            return new ArrayList<>();
        }

        return products.values().stream()
                .filter(product -> product.createdDate().isAfter(date))
                .collect(Collectors.toList());
    }

    public List<Product> getModifiedProducts() {
        return products.values().stream()
                .filter(product -> !product.createdDate().equals(product.modifiedDate()))
                .collect(Collectors.toList());
    }
}