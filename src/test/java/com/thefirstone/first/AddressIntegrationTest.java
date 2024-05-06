package com.thefirstone.first;

import com.thefirstone.first.model.Address;
import com.thefirstone.first.repository.AddressRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AddressIntegrationTest {

    @Inject
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    int port;
    @Inject
    private AddressRepository addressRepository;

    @BeforeEach
    public void createInitialData() {

        final int numberOfAddresses = 10;
        for (int i = 1; i <= numberOfAddresses; i++) {
            final Address address = new Address("Augusta Cesarca", i);
            addressRepository.save(address);
        }
    }

    @Test
    public void getById() throws Exception {
        final ResponseEntity<Address> response = this.testRestTemplate.getForEntity("http://localhost:" + port + "/address/id/{id}", Address.class, "3");

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getAll() {
        final ResponseEntity<List> response = this.testRestTemplate.getForEntity("http://localhost:" + port + "/address/all", List.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getById2() throws Exception {
        // this can work or that below
//        final ResponseEntity<Address> response = this.testRestTemplate.getForEntity("http://localhost:"+ port+ "/address/id/{id}", Address.class, "3");

        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        final ResponseEntity<Address> response = this.testRestTemplate.exchange("http://localhost:" + port + "/address/id/{id}", HttpMethod.GET, entity, Address.class, "3");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getAll2() {
        final ResponseEntity<List> response = this.testRestTemplate.getForEntity("http://localhost:" + port + "/address/all", List.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
