package dev.patika.vet.dto.response.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerResponse {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;
}
