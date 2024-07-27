package dev.patika.vet.dto.request.animal;

import dev.patika.vet.entities.Customer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AnimalUpdateRequest {
    @Positive(message = "ID değeri pozitif olmalı")
    private int id;

    @NotNull(message = "Hayvan adı boş olamaz")
    private String name;

    private String species;

    private String breed;

    private String gender;

    private String colour;

    private LocalDate dateOfBirth;

    @NotNull(message = "Animal customer ID boş olamaz")
    private Customer customer;


    public int getID() {
       return this.id=id;
    }
}
