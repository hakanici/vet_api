package dev.patika.vet.dto.response.appointment;

import dev.patika.vet.entities.Animal;
import dev.patika.vet.entities.Doctor;
import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class AppointmentGetAllResponse {

    private LocalDateTime dateTime;
    private Doctor doctor;
    private Animal animal;
}
