package dev.patika.vet.dto.response.available_date;

import dev.patika.vet.entities.Doctor;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class AvailableGetAllResponse {

    private LocalDate date;
    private Doctor doctors;
}
