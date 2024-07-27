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

public class AvailableDateUpdateRequest {

    @NotNull(message = "ID boş olamaz")
    private int id;

    @NotNull(message = "Date boş olamaz")
    private LocalDate date;

    @NotNull(message = "Doctor ID boş olamaz")
    private int doctorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}
