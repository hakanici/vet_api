package dev.patika.vet.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerUpdateRequest {
    @NotNull(message = "ID boş olamaz")
    private int id;

    @NotNull(message = "Customer name boş olamaz")
    private String name;

    @NotNull(message = "Customer phone boş olamaz")
    private String phone;

    @NotNull(message = "Customer email boş olamaz")
    @Email(message = "Geçerli bir email adresi giriniz")
    private String email;

    @NotNull(message = "Customer address boş olamaz")
    private String address;

    @NotNull(message = "Customer city boş olamaz")
    private String city;

}
