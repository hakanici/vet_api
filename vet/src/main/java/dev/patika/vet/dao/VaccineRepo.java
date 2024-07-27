package dev.patika.vet.dao;

import dev.patika.vet.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface VaccineRepo extends JpaRepository<Vaccine, Integer> {
    boolean existsVaccineByCodeAndNameAndAnimalId(String code, String name, int id);
    boolean existsByEndDate(LocalDate endDate);
    long countVaccineByNameAndCodeAndAnimalId(String name, String code, int animalId);
    List<Vaccine> findByAnimalId(int animalId);
    List<Vaccine> findByEndDateBetween(LocalDate firstDate, LocalDate endDate);
    List<Vaccine> findByEndDateAfterOrderByEndDate(LocalDate endDate);
    Vaccine findByStartDate(LocalDate startDate);

}
