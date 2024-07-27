package dev.patika.vet.business.abstracts;

import dev.patika.vet.entities.Animal;
import dev.patika.vet.entities.Customer;

import java.util.List;


public interface ICustomerService {

    Customer save(Customer customer);

    Customer get(int id);

    Customer update(Customer customer);

    boolean delete(int id);

    List<Customer> getByCustomerName(String name);

    List<Animal> getByAnimalId(int id);

    List<Customer> findAll();


}
