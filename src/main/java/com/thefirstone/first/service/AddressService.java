package com.thefirstone.first.service;

import com.thefirstone.first.model.Address;
import com.thefirstone.first.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Inject private AddressRepository addressRepository;

//    public List<Address> getAll() {
//        final Iterable<Address> all = addressRepository.findAll();
//        return all.iterator().;
//    }

    public Optional<Address> getByUserId(Long id) {
        return Optional.ofNullable(addressRepository.findByUserId(id));
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
