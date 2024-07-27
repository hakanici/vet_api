package dev.patika.vet.dao;

import dev.patika.vet.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Integer> {
    List<Animal> findByCustomerId(int animalCustomerId);
    List<Animal> findByName(String name);
    List<Animal> findByCustomerName(String name);
}

