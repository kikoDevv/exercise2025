package org.example.repository;

import org.example.entities.User;
import java.util.*;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void addUser(User user) {
        users.put(user.id(), user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void updateUser(User user) {
        users.put(user.id(), user);
    }

    @Override
    public void deleteUser(String id) {
        users.remove(id);
    }
}
