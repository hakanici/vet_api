package dev.patika.vet.business.abstracts;
import dev.patika.vet.entities.Animal;
import java.util.List;


public interface IAnimalService {

    Animal save(Animal animal);

    Animal get(int id);

    Animal update(Animal animal);

    boolean delete(int id);

    List<Animal> getAnimalByName(String name);

    List<Animal> getCustomerById(int animalCustomerId);

    List<Animal> getCustomerByName(String name);

    List<Animal> findAll();
}
