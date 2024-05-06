package com.thefirstone.first.dto;

import com.thefirstone.first.model.Address;
import com.thefirstone.first.model.Role;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class LoginResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String firstName, String lastName, String email, String role, String token) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.token = token;
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

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
