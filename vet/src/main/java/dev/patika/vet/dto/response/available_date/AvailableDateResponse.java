package dev.patika.vet.dto.response.available_date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AvailableDateResponse {

    private int id;
    private LocalDate availableDate;
}
