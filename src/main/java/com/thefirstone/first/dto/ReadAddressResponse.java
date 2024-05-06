package com.thefirstone.first.dto;

import java.util.Optional;

public class ReadAddressResponse {

    private String street;

    private Optional<Integer> number;

    public ReadAddressResponse() {
    }

    public ReadAddressResponse(String street, Optional<Integer> number) {
        this.street = street;
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public Optional<Integer> getNumber() {
        return number;
    }
}
