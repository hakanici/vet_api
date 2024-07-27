package dev.patika.vet.dao;

import dev.patika.vet.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface AvailableDateRepo extends JpaRepository<AvailableDate, Integer> {

    boolean existsByDateAndDoctors_Id(LocalDate date, int id);
    boolean existsByIdAndDateAndDoctors_Id(int dateId, LocalDate date, int id);
    AvailableDate findByDoctors_Id(int id);
    AvailableDate findByDate(LocalDate date);

    @Query("select a.id from AvailableDate a where a.date = :endDate and a.doctors.id = :doctorId")
    Long findByAvailableIdInEndDateAndDoctorId(@Param("endDate") LocalDate endDate, @Param("doctorId") int doctorId);
}
