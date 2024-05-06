package com.thefirstone.first.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.thefirstone.first.exception.NotFoundException;
import com.thefirstone.first.model.Address;
import com.thefirstone.first.repository.AddressRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    @Inject
    private AddressRepository addressRepository;

    public Address createAddress(String street, Integer number) {
        final Address address = new Address(street, number);
        return addressRepository.save(address);
    }

    public Address updateAddressNumber(Long id, Integer number) {
        final Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            throw new NotFoundException("Address with id " + id + " not found!");
        }
        address.get().setNumber(number);
        return addressRepository.save(address.get());
    }

    public boolean deleteAddress(Long id) {
        addressRepository.deleteById(id);
        return true;
    }
}
