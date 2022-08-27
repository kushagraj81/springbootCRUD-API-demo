package com.example.demo.dogs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DogsRepo extends JpaRepository<Dog, Long> {
    public List<Dog> findByname(String title);
    public Optional<Dog> findById(UUID id);
}
