package dev.patika.vet.business.concretes;

import dev.patika.vet.business.abstracts.IDoctorService;
import dev.patika.vet.core.exception.ForUpdateNotFoundIdException;
import dev.patika.vet.core.exception.NotFoundException;
import dev.patika.vet.core.exception.NotUniqueValues;
import dev.patika.vet.core.utilities.Msg;
import dev.patika.vet.dao.DoctorRepo;
import dev.patika.vet.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class DoctorManager implements IDoctorService {

    private final DoctorRepo doctorRepo;

    public DoctorManager(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }


    @Override
    public Doctor save(Doctor doctor) { // Criteria 15 - Is the doctor registered according to project requirements?
        if(doctorRepo.existsByEmailOrPhone(doctor.getEmail(),doctor.getPhone())){
            throw new NotUniqueValues(Msg.NOT_UNIQUE);
        }
        return doctorRepo.save(doctor);
    }

    @Override
    public Doctor get(int id) {
        return this.doctorRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Doctor update(Doctor doctor) {
        doctorRepo.findById(doctor.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Msg.UPDATE_NOT_FOUND_ID));
        return doctorRepo.save(doctor);
    }

    @Override
    public boolean delete(int id) {
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return true;
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepo.findAll();
    }
}
