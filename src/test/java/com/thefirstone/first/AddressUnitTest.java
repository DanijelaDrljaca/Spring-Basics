package com.thefirstone.first;

import com.thefirstone.first.controller.AddressController;
import com.thefirstone.first.model.Address;
import com.thefirstone.first.repository.AddressRepository;
import com.thefirstone.first.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import javax.inject.Inject;

@WebMvcTest(AddressController.class)
public class AddressUnitTest {
    @Inject
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @MockBean
    private AddressRepository addressRepository;

    @BeforeEach
    public void createInitialAddresses() {
        final int numberOfAddresses = 10;
        for (int i = 1; i <= numberOfAddresses; i++) {
            final Address address = new Address("Augusta Cesarca", i);
            addressRepository.save(address);
        }
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/address/all")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"
                ));
        Mockito.verify(addressService, Mockito.times(1)).getAll();
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get("/address/id/{id}", "3")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":3,\"street\":\"Augusta Cesarca\",\"number\":3}"
                ));
        Mockito.verify(addressService, Mockito.times(1)).getByUserId(3L);
    }

}
