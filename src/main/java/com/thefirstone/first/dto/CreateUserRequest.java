package com.thefirstone.first.dto;

import com.thefirstone.first.model.Address;

public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Address address;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String firstName, String lastName, String email, String password, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }
}
