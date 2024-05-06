package com.thefirstone.first.dto;

public class UpdateUserRequest {
    private String firstName;

    private String lastName;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
