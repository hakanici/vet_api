package dev.patika.vet.business.concretes;

import dev.patika.vet.business.abstracts.ICustomerService;
import dev.patika.vet.core.exception.*;
import dev.patika.vet.core.utilities.Msg;
import dev.patika.vet.dao.CustomerRepo;
import dev.patika.vet.entities.Animal;
import dev.patika.vet.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class CustomerManager implements ICustomerService {
    private final CustomerRepo customerRepo;

    public CustomerManager(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    @Override
    public Customer save(Customer customer) { // Criteria 10 - Is the animal owner registered according to project requirements?
        if(customerRepo.existsByEmail(customer.getEmail()) || customerRepo.existsByPhone(customer.getPhone())){
            throw new NotUniqueValues(Msg.NOT_UNIQUE);
        }
        return customerRepo.save(customer);
    }

    @Override
    public Customer get(int id) {
        return this.customerRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Customer update(Customer customer) {
        customerRepo.findById(customer.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Msg.UPDATE_NOT_FOUND_ID));
        return customerRepo.save(customer);
    }

    @Override
    public boolean delete(int id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }

    @Override
    public List<Customer> getByCustomerName(String name) {
        if(customerRepo.findByName(name).isEmpty()){
            throw new NotFoundObjectRequest(Msg.NOT_FOUND);
        }
        return customerRepo.findByName(name);
    }

    @Override
    public List<Animal> getByAnimalId(int id) {
        if(customerRepo.findById(id).isEmpty()){
            throw new NotFoundCustomerException(Msg.NOT_FOUND_CUSTOMER);
        }
        if(get(id).getAnimalList().isEmpty()){
            throw new NotFoundObjectRequest(Msg.NOT_FOUND);
        }
        return get(id).getAnimalList();
    }

    @Override
    public List<Customer> findAll(){
        return customerRepo.findAll();
    }



}
