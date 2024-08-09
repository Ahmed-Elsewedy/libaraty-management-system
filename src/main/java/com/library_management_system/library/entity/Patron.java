package com.library_management_system.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false, length = 11)
    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 digits long")
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
