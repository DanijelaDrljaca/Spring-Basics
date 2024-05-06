package com.thefirstone.first.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.util.Optional;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
    @GenericGenerator(name = "native")
    // up generator parameter and this third annotation is necessary since Hibernate 5.00 just for SEQUENCE AND AUTO, IDENTITY WORKS, TH THIRD ONE I DID NOT TRY
    private Long id;
    private String street;

    private Integer number;

    public Address() {
    }

    public Address(String street) {
        this.street = street;
    }

    public Address(String street, Integer number) {
        this.street = street;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
