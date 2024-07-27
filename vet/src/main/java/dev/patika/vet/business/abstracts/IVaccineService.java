package dev.patika.vet.business.abstracts;

import dev.patika.vet.entities.Vaccine;


import java.time.LocalDate;
import java.util.List;


public interface IVaccineService {

    Vaccine save(Vaccine vaccine);

    Vaccine get(int id);

    Vaccine update(Vaccine vaccine);

    boolean delete(int id);

    List<Vaccine> getAnimalVaccineById(int id);

    List<Vaccine> findVaccinesByDateRange(LocalDate starDate, LocalDate endDate);

    List<Vaccine> findAll();
}
