package dev.patika.vet.business.concretes;

import dev.patika.vet.business.abstracts.IAnimalService;
import dev.patika.vet.core.exception.ForUpdateNotFoundIdException;
import dev.patika.vet.core.exception.NotFoundCustomerException;
import dev.patika.vet.core.exception.NotFoundException;
import dev.patika.vet.core.exception.NotFoundObjectRequest;
import dev.patika.vet.core.utilities.Msg;
import dev.patika.vet.dao.AnimalRepo;
import dev.patika.vet.dao.CustomerRepo;
import dev.patika.vet.entities.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class AnimalManager implements IAnimalService {
    private final AnimalRepo animalRepo;
    private final CustomerRepo customerRepo;

    public AnimalManager(AnimalRepo animalRepo, CustomerRepo customerRepo) {
        this.animalRepo = animalRepo;
        this.customerRepo = customerRepo;
    }

    @Override
    public Animal save(Animal animal) {
        if (customerRepo.findById(animal.getCustomer().getId()).isEmpty()) {
            throw new NotFoundCustomerException(Msg.NOT_FOUND_CUSTOMER);
        }
        return this.animalRepo.save(animal);
    }

    @Override
    public Animal get(int id) {
        return this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Animal update(Animal animal) {
        animalRepo.findById(animal.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Msg.UPDATE_NOT_FOUND_ID));
        if (customerRepo.findById(animal.getCustomer().getId()).isEmpty()) {
            throw new NotFoundCustomerException(Msg.NOT_FOUND_CUSTOMER);
        }
        this.get(animal.getId());
        return this.animalRepo.save(animal);
    }
    @Override
    public List<Animal> getAnimalByName(String name) { // Criteria 13 - Are animals filtered by name?
        if(animalRepo.findByName(name).isEmpty()){
            throw new NotFoundObjectRequest(Msg.NOT_FOUND);
        }
        return animalRepo.findByName(name);
    }

    @Override
    public boolean delete(int id) {
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return true;
    }

    @Override
    public List<Animal> getCustomerById(int animalCustomerId) {
        if(animalRepo.findByCustomerId(animalCustomerId).isEmpty()){
            throw new NotFoundCustomerException(Msg.NOT_FOUND_CUSTOMER);
        }
        return animalRepo.findByCustomerId(animalCustomerId);
    }

    @Override
    public List<Animal> getCustomerByName(String name) {
        if(animalRepo.findByCustomerName(name).isEmpty()){
            throw new NotFoundObjectRequest(Msg.NOT_FOUND);
        }
        return animalRepo.findByCustomerName(name);
    }

    @Override
    public List<Animal> findAll() {
        return animalRepo.findAll();
    }


}
