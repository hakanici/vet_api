package dev.patika.vet.dao;

import dev.patika.vet.entities.Animal;
import dev.patika.vet.entities.Appointment;
import dev.patika.vet.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

    boolean existsByDoctor_Id(int id);
    boolean existsByDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    boolean existsByAvailableDate_Id(int id);
    boolean existsByDoctor_IdAndAnimal_Id(int doctor_id, int animal_id);
    List<Appointment> findByDateTimeBetweenAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor);
    List<Appointment> findByDateTimeBetweenAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal);
}
