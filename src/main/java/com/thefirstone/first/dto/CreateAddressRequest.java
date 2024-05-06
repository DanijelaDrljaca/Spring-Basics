package com.thefirstone.first.dto;

public class CreateAddressRequest {
    private String street;

    private Integer number;

    public CreateAddressRequest() {
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
