package dev.patika.vet.dto.request.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DoctorUpdateRequest {

    @NotNull(message = "ID boş olamaz")
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
