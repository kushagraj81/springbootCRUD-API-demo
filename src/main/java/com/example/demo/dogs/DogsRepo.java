package com.example.demo.dogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//the second argument is for Id type here in this definition
//our deletebyID was not workign because We had Long in our id type
//public interface DogsRepo extends JpaRepository<Dog, Long>
public interface DogsRepo extends JpaRepository<Dog, UUID> {
    public List<Dog> findByname(String title);
    public Optional<Dog> findById(UUID id);

}
