package dev.patika.vet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id")
    private int id;

    @Column(name = "vaccine_name")
    @NotNull
    private String name;

    @Column(name = "vaccine_code")
    private String code;

    @Column(name = "protectionStartDate")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Column(name = "protectionEndDate")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vaccine_animal_id",referencedColumnName = "animal_id")
    private Animal animal;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return endDate;
    }

    public Animal getAnimal() {
        return animal;
    }

    public int getId() {
        return this.id=id;
    }
}
