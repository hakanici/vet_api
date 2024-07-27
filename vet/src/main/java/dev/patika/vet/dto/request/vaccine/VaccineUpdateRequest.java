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


public class VaccineUpdateRequest {

    @NotNull(message = "ID boş olamaz")
    private int id;

    @NotNull(message = "Vaccine name boş bırakılamaz")
    private String name;

    @NotNull(message = "Code boş bırakılamaz")
    private String code;

    @NotNull(message = "Start date boş bırakılamaz")
    private LocalDate startDate;

    @NotNull(message = "End date boş bırakılamaz")
    private LocalDate endDate;

    @NotNull(message = "Animal ID boş bırakılamaz")
    private Animal animal;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
