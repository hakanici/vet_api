package dev.patika.vet.dto.response.customer;

import dev.patika.vet.entities.Animal;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CustomerListResponse {

    List<Animal> animalList;
}
