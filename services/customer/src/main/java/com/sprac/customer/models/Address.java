package com.sprac.customer.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "Address")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String houseNumber;
    private String zipCode;
}
