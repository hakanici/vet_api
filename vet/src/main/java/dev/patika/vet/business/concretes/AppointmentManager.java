package dev.patika.vet.business.concretes;

import dev.patika.vet.business.abstracts.IAppointmentService;
import dev.patika.vet.core.exception.*;
import dev.patika.vet.core.utilities.Msg;
import dev.patika.vet.dao.AnimalRepo;
import dev.patika.vet.dao.AppointmentRepo;
import dev.patika.vet.dao.AvailableDateRepo;
import dev.patika.vet.dao.DoctorRepo;
import dev.patika.vet.entities.Animal;
import dev.patika.vet.entities.Appointment;
import dev.patika.vet.entities.AvailableDate;
import dev.patika.vet.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service


public class AppointmentManager implements IAppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final DoctorRepo doctorRepo;
    private final AvailableDateRepo availableDateRepo;
    private final AnimalRepo animalRepo;

    public AppointmentManager(AppointmentRepo appointmentRepo, DoctorRepo doctorRepo, AvailableDateRepo availableDateRepo, AnimalRepo animalRepo) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.availableDateRepo = availableDateRepo;
        this.animalRepo = animalRepo;
    }

    @Override
    public Appointment save(Appointment appointment) {
        if (!doctorRepo.existsById(appointment.getDoctor().getId()) || !animalRepo.existsById(appointment.getAnimal().getId())) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }

        if(availableDateRepo.findByAvailableIdInEndDateAndDoctorId(appointment.getAppointmentDate().toLocalDate(), appointment.getDoctor().getId()) == null){
            throw new DoctorDaysConflictException(Msg.DAYS_CONFLICT);
        }
        // Find the available date ID for the appointment's date and doctor
        int availableId = Math.toIntExact(availableDateRepo.findByAvailableIdInEndDateAndDoctorId(appointment.getAppointmentDate().toLocalDate(), appointment.getDoctor().getId()));

        // Check if the available date exists for the specified date and doctor
        if (availableDateRepo.existsByIdAndDateAndDoctors_Id(availableId, appointment.getAppointmentDate().toLocalDate(), appointment.getDoctor().getId())) {
            // Check for appointment conflicts
            for (int i = 0; i < appointmentRepo.findAll().size(); i++) {
                if (appointmentRepo.existsByDoctor_Id(appointment.getDoctor().getId())) {
                    if (Duration.between(appointment.getAppointmentDate(), appointmentRepo.findAll().get(i).getAppointmentDate()).toHours() == 0) { // Section 18 - Save an appointment
                        throw new AppointmentConflictException(Msg.APPOINTMENT_CONFLICT);
                    }
                }
            }
            AvailableDate availableDate = availableDateRepo.findById(availableId).orElseThrow();
            appointment.setAvailableDate(availableDate);
            return appointmentRepo.save(appointment);
        }
        throw new DoctorDaysConflictException(Msg.DAYS_CONFLICT);
    }


    @Override
    public Appointment get(int id) {
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Appointment update(Appointment appointment) {
        this.get(appointment.getId());
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public boolean delete(int id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return true;
    }

    @Override
    public List<Appointment> findAppointmentsByDateRangeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor) {
        if (doctorRepo.findById(doctor.getId()).isEmpty()) {
            throw new NotFoundDoctorException(Msg.NOT_FOUND_DOCTOR);
        }
        if (!appointmentRepo.existsByDateTimeBetween(startDate, endDate)) {
            throw new NotFoundAppointment(Msg.NOT_FOUND_APPOINTMENT);
        }
        return appointmentRepo.findByDateTimeBetweenAndDoctor(startDate, endDate, doctor);
    }

    @Override
    public List<Appointment> findAppointmentsByDateRangeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal) {
        if (appointmentRepo.findById(animal.getId()).isEmpty()) {
            throw new NotFoundAnimalException(Msg.NOT_FOUND_ANIMAL);
        }
        if (!appointmentRepo.existsByDateTimeBetween(startDate, endDate)) {
            throw new NotFoundAppointment(Msg.NOT_FOUND_APPOINTMENT);
        }
        return appointmentRepo.findByDateTimeBetweenAndAnimal(startDate, endDate, animal);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepo.findAll();
    }
}
