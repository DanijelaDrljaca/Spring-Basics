package com.thefirstone.first.controller;

import com.thefirstone.first.dto.CreateAddressRequest;
import com.thefirstone.first.exception.NotFoundException;
import com.thefirstone.first.model.Address;
import com.thefirstone.first.model.User;
import com.thefirstone.first.service.AddressService;
import com.thefirstone.first.service.UserService;
import com.thefirstone.first.util.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Inject
    private AddressService addressService;

    @Inject private ApplicationContext applicationContext;

    @Inject
    UserService userService;

    @GetMapping(value = "/id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    // todo danijela fix this one
    public ResponseEntity<Address> getByUserId(@PathVariable Long id) {
        final Long currentUserId = applicationContext.getCurrentUser().getId();
        final Optional<Address> byId = addressService.getByUserId(currentUserId);
        if (byId.isPresent()) {
            return new ResponseEntity<>(byId.get(), HttpStatus.OK);
        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Optional parameter as a message");
        throw new NotFoundException("Address with id " + id + " not found!");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> createAddress(@RequestBody CreateAddressRequest createAddressRequest) {
//        final String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        final User user = userService.findByEmail(email);
        final User user = applicationContext.getCurrentUser();
        final Optional<Address> existingAddress = addressService.getByUserId(user.getId());
        final Address address;
            final String street = createAddressRequest.getStreet();
        final Integer number = createAddressRequest.getNumber();
        if (existingAddress.isPresent()) {
            address = existingAddress.get();
            address.setStreet(street);
            address.setNumber(number);
        } else {
            address = new Address(street, number);
            user.setAddress(address);
        }
        addressService.save(address);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Address>> getAll() {
        return new ResponseEntity<>(addressService.getAll(), HttpStatus.OK);
    }
}
