package com.thefirstone.first.dto;

import java.util.Optional;

public class ReadUserResponse {
    private String firstName;

    private String lastName;

    private String email;

    public ReadUserResponse() {
    }

    public ReadUserResponse(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
}
