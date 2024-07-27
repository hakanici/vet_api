package dev.patika.vet.business.concretes;

import dev.patika.vet.business.abstracts.IVaccineService;
import dev.patika.vet.core.exception.*;
import dev.patika.vet.core.utilities.Msg;
import dev.patika.vet.dao.AnimalRepo;
import dev.patika.vet.dao.VaccineRepo;
import dev.patika.vet.entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service


public class VaccineManager implements IVaccineService {

    private final VaccineRepo vaccineRepo;
    private final AnimalRepo animalRepo;

    public VaccineManager(VaccineRepo vaccineRepo, AnimalRepo animalRepo) {
        this.vaccineRepo = vaccineRepo;
        this.animalRepo = animalRepo;
    }


    @Override
    public Vaccine save(Vaccine vaccine) { // Criteria 21 - Proje isterlerine göre hayvana ait aşı kaydediliyor mu?

        // Criteria 22 - Is the end date of protection checked during the new vaccine registration process? Is the registration of vaccines with expired protection dates done and the registration of unprotected vaccines blocked?
        if (animalRepo.findById(vaccine.getAnimal().getId()).isEmpty()) {
            throw new NotFoundAnimalException(Msg.NOT_FOUND_ANIMAL);
        }
        if (vaccineRepo.existsVaccineByCodeAndNameAndAnimalId(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
            if(vaccineRepo.findByEndDateAfterOrderByEndDate(vaccine.getStartDate()).isEmpty()){ // Section 22 - Vaccine day check
                if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getFinishDate()) < 0) {
                    throw new NoneSenseInformationException(Msg.BAD_DATE);
                }
                return vaccineRepo.save(vaccine);
            }
            throw new DateMismatchException(Msg.DATE_MISMATCH);
        }
        if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getFinishDate()) < 0) {
            throw new NoneSenseInformationException(Msg.BAD_DATE);
        }
        return vaccineRepo.save(vaccine);
    }

    @Override
    public Vaccine get(int id) {
        return this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        get(vaccine.getId());
        if (vaccineRepo.existsVaccineByCodeAndNameAndAnimalId(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
            if(vaccineRepo.findByEndDateAfterOrderByEndDate(vaccine.getStartDate()).isEmpty()){
                if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getFinishDate()) < 0) {
                    throw new NoneSenseInformationException(Msg.BAD_DATE);
                }
                return vaccineRepo.save(vaccine);
            }
            throw new ForceUpdateException(Msg.FORCE_UPDATE);
        }
        return save(vaccine);
    }

    @Override
    public boolean delete(int id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    @Override
    public List<Vaccine> getAnimalVaccineById(int id) {
        if (vaccineRepo.findByAnimalId(id).isEmpty()) {
            throw new NotFoundAnimalException(Msg.NOT_FOUND_ANIMAL);
        }
        return vaccineRepo.findByAnimalId(id);
    }

    @Override
    public List<Vaccine> findVaccinesByDateRange(LocalDate starDate, LocalDate endDate) {
        if (vaccineRepo.findByEndDateBetween(starDate,endDate).isEmpty()) {
            throw new NotFoundObjectRequest(Msg.NOT_FOUND);
        }
        return vaccineRepo.findByEndDateBetween(starDate,endDate);
    }

    @Override
    public List<Vaccine> findAll() {
        return vaccineRepo.findAll();
    }
}
