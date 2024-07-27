package dev.patika.vet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private int id;

    @Column(name = "appointment_date")
    private LocalDateTime dateTime;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_doctor_id",referencedColumnName = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_animal_id",referencedColumnName = "animal_id")
    private Animal animal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_available_date_id",referencedColumnName = "available_date_id")
    private AvailableDate availableDate;


    public int getId() {
        return this.id=id;
    }

    public LocalDateTime getAppointmentDate() {
        return dateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Animal getAnimal() {
        return animal;
    }

    public AvailableDate getAvailableDate() {
        return availableDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.dateTime = appointmentDate;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void setAvailableDate(AvailableDate availableDate) {
        this.availableDate = availableDate;
    }
}
