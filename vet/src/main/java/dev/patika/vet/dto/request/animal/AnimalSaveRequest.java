package dev.patika.vet.dto.request.animal;

import dev.patika.vet.entities.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AnimalSaveRequest {

    @NotNull(message = "Animal name boş olamaz")
    private String name;

    @NotNull(message = "Species boş olamaz")
    private String species;

    @NotNull(message = "Breed boş olamaz")
    private String breed;

    @NotNull(message = "Gender boş olamaz")
    private String gender;

    @NotNull(message = "Colour boş olamaz")
    private String colour;

    @NotNull(message = "Date of Birth boş olamaz")
    private LocalDate dateOfBirth;

    @NotNull(message = "Animal customer ID boş olamaz.")
    private Customer customer;
}
