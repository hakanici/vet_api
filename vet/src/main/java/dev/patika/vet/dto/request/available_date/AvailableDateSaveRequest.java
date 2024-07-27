package dev.patika.vet.dto.request.available_date;



import dev.patika.vet.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AvailableDateSaveRequest {


    @NotNull(message = "Date boş olamaz")
    private LocalDate date;

    @NotNull(message = "Doctor ID boş olamaz")
    private Doctor doctors;
}
