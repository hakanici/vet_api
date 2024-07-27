package dev.patika.vet.dto.request.doctor;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSaveRequest {


    @NotNull(message = "Doctor name boş olamaz")
    private String name;

    @NotNull(message = "Doctor phone boş olamaz")
    private String phone;

    @NotNull(message = "Doctor email boş olamaz")
    @Email(message = "Geçerli bir email adresi giriniz")
    private String email;

    @NotNull(message = "Doctor address boş olamaz")
    private String address;

    @NotNull(message = "Doctor city boş olamaz")
    private String city;
}
