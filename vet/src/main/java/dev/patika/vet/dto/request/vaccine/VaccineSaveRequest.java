package dev.patika.vet.dto.request.vaccine;



import dev.patika.vet.entities.Animal;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VaccineSaveRequest {


    @NotNull(message = "Vaccine name boş olamaz")
    private String name;

    @NotNull(message = "Vaccine code boş olamaz")
    private String code;

    @NotNull(message = "Vaccine start date boş olamaz")
    private LocalDate startDate;

    @NotNull(message = "Vaccine end date boş olamaz")
    private LocalDate endDate;

    @NotNull(message = "Animal ID boş olamaz")
    private Animal animal;
}
