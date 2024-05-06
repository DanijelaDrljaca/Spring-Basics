package com.thefirstone.first.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.thefirstone.first.model.Address;
import com.thefirstone.first.repository.AddressRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {
    @Inject
    private AddressRepository addressRepository;

    public List<Address> findAllAddresses() {
        System.out.println("FIND ALL CALLED!");
        return addressRepository.findAll();
    }

    public int countAllAddresses() {
        System.out.println("COUNT ALL CALLED!");
        return (int) addressRepository.count();
    }

    public Address findAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }
}
