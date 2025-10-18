package org.example.repository;

import org.example.entities.User;
import java.util.*;

/**
 * Simulates a database-backed repository.
 * In a real application, this would connect to an actual database.
 */
public class DatabaseUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();
    private final String databaseName;

    public DatabaseUserRepository(String databaseName) {
        this.databaseName = databaseName;
        System.out.println("DatabaseUserRepository initialized with database: " + databaseName);
    }

    @Override
    public void addUser(User user) {
        System.out.println("Adding user to database [" + databaseName + "]: " + user);
        users.put(user.id(), user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        System.out.println("Fetching user from database [" + databaseName + "] with ID: " + id);
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> getAllUsers() {
        System.out.println("Fetching all users from database [" + databaseName + "]");
        return new ArrayList<>(users.values());
    }

    @Override
    public void updateUser(User user) {
        System.out.println("Updating user in database [" + databaseName + "]: " + user);
        users.put(user.id(), user);
    }

    @Override
    public void deleteUser(String id) {
        System.out.println("Deleting user from database [" + databaseName + "] with ID: " + id);
        users.remove(id);
    }
}
