package dev.patika.vet.dto.request.appointment;


import dev.patika.vet.entities.Animal;
import dev.patika.vet.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AppointmentUpdateRequest {

    @NotNull(message = "ID boş olamaz")
    private int id;

    @NotNull(message = "Animal ID boş olamaz")
    private Animal animal;

    @NotNull(message = "Doctor ID boş olamaz")
    private Doctor doctor;

    @NotNull(message = "Date Time boş olamaz")
    private LocalDateTime dateTime;

    public int getID() {
        return this.id=id;
    }
}
