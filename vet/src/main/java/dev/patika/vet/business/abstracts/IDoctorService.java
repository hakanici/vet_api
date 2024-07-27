package dev.patika.vet.business.abstracts;


import dev.patika.vet.entities.Doctor;


import java.util.List;

public interface IDoctorService {

    Doctor save(Doctor doctor);

    Doctor get(int id);

    Doctor update(Doctor doctor);

    boolean delete(int id);

    List<Doctor> findAll();
}
