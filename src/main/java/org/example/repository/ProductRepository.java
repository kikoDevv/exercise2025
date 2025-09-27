package org.example.repository;package org.example.repository;

import org.example.entities.Product;

import org.example.entities.Product;import java.util.List;

import java.util.Optional;

import java.util.List;

import java.util.Optional;public interface ProductRepository {



/**    void addProduct(Product product);

 * Repository interface for Product data access operations.

 * This interface defines the contract for managing Product entities    Optional<Product> getProductById(String id);

 * in the data storage layer.

 */    List<Product> getAllProducts();

public interface ProductRepository {

        void updateProduct(Product product);

    /**}

     *
     *
     * @param product
     * @throws IllegalArgumentException
     */
    void addProduct(Product product);

    /**
     * Retrieves a product by its ID.
     *
     * @param id
     * @return
     */
    Optional<Product> getProductById(String id);

    /**
     * Retrieves all products from the repository.
     *
     * @return
     */
    List<Product> getAllProducts();

    /**
     * Updates an existing product in the repository.
     *
     * @param product
     * @throws IllegalArgumentException 
     */
    void updateProduct(Product product);
}