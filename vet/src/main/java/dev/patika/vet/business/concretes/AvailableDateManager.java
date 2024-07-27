package dev.patika.vet.business.concretes;

import dev.patika.vet.business.abstracts.IAvailableDateService;
import dev.patika.vet.core.exception.AppointmentAlreadyExists;
import dev.patika.vet.core.exception.NotFoundDoctorException;
import dev.patika.vet.core.exception.NotFoundException;
import dev.patika.vet.core.exception.SameValuesException;
import dev.patika.vet.core.utilities.Msg;
import dev.patika.vet.dao.AppointmentRepo;
import dev.patika.vet.dao.AvailableDateRepo;
import dev.patika.vet.dao.DoctorRepo;
import dev.patika.vet.entities.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service


public class AvailableDateManager implements IAvailableDateService {
    private final AvailableDateRepo availableDateRepo;
    private final DoctorRepo doctorRepo;
    private final AppointmentRepo appointmentRepo;

    public AvailableDateManager(AvailableDateRepo availableDateRepo, DoctorRepo doctorRepo, AppointmentRepo appointmentRepo) {
        this.availableDateRepo = availableDateRepo;
        this.doctorRepo = doctorRepo;
        this.appointmentRepo = appointmentRepo;
    }


    @Override
    public AvailableDate save(AvailableDate availableDate) { // Criteria 16 - Is the doctor's available day recorded according to project requirements?
        if(doctorRepo.findById(availableDate.getDoctors().getId()).isEmpty()){
            throw new NotFoundDoctorException(Msg.NOT_FOUND_DOCTOR);
        }
        if(availableDateRepo.existsByDateAndDoctors_Id(availableDate.getAvailableDate(),availableDate.getDoctors().getId())){
            throw new SameValuesException(Msg.SAME_VALUES);
        }
        return availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate get(int id) {
        return this.availableDateRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        if(appointmentRepo.existsByAvailableDate_Id(availableDate.getId())){
            throw new AppointmentAlreadyExists(Msg.EXISTING_APPOINTMENT);
        }
        availableDateRepo.findById(availableDate.getId()).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
        if(doctorRepo.findById(availableDate.getDoctors().getId()).isEmpty()){
            throw new NotFoundDoctorException(Msg.NOT_FOUND_DOCTOR);
        }
        if(availableDateRepo.existsByDateAndDoctors_Id(availableDate.getAvailableDate(),availableDate.getDoctors().getId())){
            throw new SameValuesException(Msg.SAME_VALUES);
        }
        return availableDateRepo.save(availableDate);
    }

    @Override
    public boolean delete(int id) {
        if(appointmentRepo.existsByAvailableDate_Id(id)){
            throw new AppointmentAlreadyExists(Msg.EXISTING_APPOINTMENT);
        }
        availableDateRepo.delete(get(id));
        return true;
    }

    @Override
    public List<AvailableDate> findAll() {
        return availableDateRepo.findAll();
    }
}
