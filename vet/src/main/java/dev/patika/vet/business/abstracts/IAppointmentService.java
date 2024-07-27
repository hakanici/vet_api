package dev.patika.vet.business.abstracts;


import dev.patika.vet.entities.Animal;
import dev.patika.vet.entities.Appointment;
import dev.patika.vet.entities.Doctor;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    Appointment save(Appointment appointment);

    Appointment get(int id);

    Appointment update(Appointment appointment);

    boolean delete(int id);

    List<Appointment> findAppointmentsByDateRangeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor);

    List<Appointment> findAppointmentsByDateRangeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal);

    List<Appointment> findAll();
}
