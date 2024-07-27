package dev.patika.vet.dao;

import dev.patika.vet.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    boolean existsByEmailOrPhone(String email, String phone);

}
