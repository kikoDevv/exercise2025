package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.dto.PetDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Thread,safe service for managing Pet entities.
 */
@ApplicationScoped
public class PetService {

    private final ConcurrentHashMap<Long, PetDTO> pets = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final ReentrantLock lock = new ReentrantLock();


    public PetDTO adoptPet(PetDTO pet) {
        lock.lock();
        try {
            Long id = idGenerator.getAndIncrement();
            pet.setId(id);
            pets.put(id, pet);
            return pet;
        } finally {
            lock.unlock();
        }
    }

    // -- get all the pets--

    public List<PetDTO> getAllPets() {
        return new ArrayList<>(pets.values());
    }

    // -- get pets by id--
    public Optional<PetDTO> getPetById(Long id) {
        return Optional.ofNullable(pets.get(id));
    }

    // -- reduse hunger lever---
    public Optional<PetDTO> feedPet(Long id, int amount) {
        lock.lock();
        try {
            PetDTO pet = pets.get(id);
            if (pet == null) {
                return Optional.empty();
            }

            int newHungerLevel = Math.max(0, pet.getHungerLevel() - amount);
            pet.setHungerLevel(newHungerLevel);
            return Optional.of(pet);
        } finally {
            lock.unlock();
        }
    }

    //-- play with pet--
    public Optional<PetDTO> playWithPet(Long id, int amount) {
        lock.lock();
        try {
            PetDTO pet = pets.get(id);
            if (pet == null) {
                return Optional.empty();
            }

            int newHappiness = Math.min(100, pet.getHappiness() + amount);
            pet.setHappiness(newHappiness);
            return Optional.of(pet);
        } finally {
            lock.unlock();
        }
    }

    // -- relase a pet--
    public boolean releasePet(Long id) {
        return pets.remove(id) != null;
    }

    // --get pet with pagination---
    public List<PetDTO> getPetsWithPagination(int offset, int limit) {
        return pets.values().stream()
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }

    // --Get pets filtered by species--
    public List<PetDTO> getPetsBySpecies(String species) {
        return pets.values().stream()
                .filter(pet -> pet.getSpecies().equalsIgnoreCase(species))
                .collect(Collectors.toList());
    }

    // --Get pets with sorting--
    public List<PetDTO> getPetsWithSorting(String sortBy, String order) {
        Comparator<PetDTO> comparator = getComparator(sortBy);

        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        return pets.values().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    // Get pets with pagination, filtering, and sorting--
    public List<PetDTO> getPetsWithFilters(String species, String sortBy, String order, int offset, int limit) {
        var stream = pets.values().stream();

        if (species != null && !species.trim().isEmpty()) {
            stream = stream.filter(pet -> pet.getSpecies().equalsIgnoreCase(species));
        }
        if (sortBy != null && !sortBy.trim().isEmpty()) {
            Comparator<PetDTO> comparator = getComparator(sortBy);
            if ("desc".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }
            stream = stream.sorted(comparator);
        }

        return stream.skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }

    // --total pets---
    public long getPetCount() {
        return pets.size();
    }

    // --- total filtered pets-----
    public long getPetCountBySpecies(String species) {
        return pets.values().stream()
                .filter(pet -> pet.getSpecies().equalsIgnoreCase(species))
                .count();
    }

    // --helper method--
    private Comparator<PetDTO> getComparator(String sortBy) {
        return switch (sortBy.toLowerCase()) {
            case "name" -> Comparator.comparing(PetDTO::getName, String.CASE_INSENSITIVE_ORDER);
            case "species" -> Comparator.comparing(PetDTO::getSpecies, String.CASE_INSENSITIVE_ORDER);
            case "hungerlevel" -> Comparator.comparing(PetDTO::getHungerLevel);
            case "happiness" -> Comparator.comparing(PetDTO::getHappiness);
            default -> Comparator.comparing(PetDTO::getId);
        };
    }
}
