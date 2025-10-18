package org.example.service;

import org.example.entities.User;
import org.example.repository.UserRepository;
import java.util.List;
import java.util.Optional;

/**
 * Default implementation of UserService using constructor injection.
 * Dependencies are passed through the constructor and stored as final fields.
 */
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    /**
     * Constructor injection: the repository dependency is passed as a parameter.
     * This demonstrates the core principle of constructor injection.
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        System.out.println("Registering user: " + user.name());
        userRepository.addUser(user);
    }

    @Override
    public Optional<User> findUserById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be empty");
        }
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void updateUserEmail(String id, String newEmail) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be empty");
        }
        if (newEmail == null || newEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        Optional<User> user = userRepository.getUserById(id);
        if (user.isPresent()) {
            User updatedUser = new User(id, user.get().name(), newEmail);
            userRepository.updateUser(updatedUser);
            System.out.println("Updated user email for: " + id);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
    }

    @Override
    public void removeUser(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be empty");
        }
        System.out.println("Removing user: " + id);
        userRepository.deleteUser(id);
    }
}
