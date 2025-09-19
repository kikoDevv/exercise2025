package org.example.service;

import org.example.entities.Category;
import org.example.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

    private Warehouse warehouse;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        testProduct = new Product(
            "1",
            "iPhone 15",
            Category.ELECTRONICS,
            9,
            LocalDate.now(),
            LocalDate.now()
        );
    }

    @Test
    void addProduct_Success() {
        // When
        warehouse.addProduct(testProduct);

        // Then
        List<Product> products = warehouse.getAllProducts();
        assertEquals(1, products.size());
        assertEquals("iPhone 15", products.get(0).name());
    }

    @Test
    void addProduct_NullProduct_ThrowsException() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            warehouse.addProduct(null);
        });
    }

    @Test
    void getProductById_Success() {
        // Given
        warehouse.addProduct(testProduct);

        // When
        Optional<Product> found = warehouse.getProductById("1");

        // Then
        assertTrue(found.isPresent());
        assertEquals("iPhone 15", found.get().name());
    }

    @Test
    void getProductById_NotFound() {
        // When
        Optional<Product> found = warehouse.getProductById("999");

        // Then
        assertFalse(found.isPresent());
    }

    @Test
    void getAllProducts_EmptyWarehouse() {
        // When
        List<Product> products = warehouse.getAllProducts();

        // Then
        assertTrue(products.isEmpty());
    }
}