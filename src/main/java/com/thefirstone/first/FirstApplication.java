package com.thefirstone.first;

import com.thefirstone.first.model.Address;
import com.thefirstone.first.model.Role;
import com.thefirstone.first.model.User;
import com.thefirstone.first.repository.AddressRepository;
import com.thefirstone.first.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;

@SpringBootApplication
public class FirstApplication {

    @Inject
    private AddressRepository addressRepository;

    @Inject
    private UserRepository userRepository;

    @Inject private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void addFirstData() {
        // "email" : "peraperic@mail.con", "password" : "pera1234"
        final String email = "danijela@gmail.com";
        final User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            final Address address = new Address("Augusta Cesarca", 11);
            addressRepository.save(address);
            // 1234
            final String passwordencoded = passwordEncoder.encode("1234");
            final User user = new User("Danijela", "Drljaca", email, passwordencoded, Role.ADMIN.name(), address);
            userRepository.save(user);
            System.out.println("PASSWORD ENC: " + passwordencoded);
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(FirstApplication.class, args);
    }

}
