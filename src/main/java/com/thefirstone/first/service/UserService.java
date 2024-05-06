package com.thefirstone.first.service;

import com.thefirstone.first.dto.CreateUserRequest;
import com.thefirstone.first.dto.LoginRequest;
import com.thefirstone.first.dto.ReadUserResponse;
import com.thefirstone.first.dto.UpdateUserRequest;
import com.thefirstone.first.model.Role;
import com.thefirstone.first.model.User;
import com.thefirstone.first.repository.UserRepository;
import com.thefirstone.first.util.ApplicationContext;
import com.thefirstone.first.util.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private Converter converter;

    @Inject
    private ApplicationContext applicationContext;

    public void createUser(CreateUserRequest createUserRequest) {
        final User user = new User(createUserRequest.getFirstName(), createUserRequest.getLastName(), createUserRequest.getEmail(), passwordEncoder.encode(
                createUserRequest.getPassword()), Role.USER.name());
        userRepository.save(user);
    }

    public User login(LoginRequest loginRequest) {
        return userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<ReadUserResponse> readUser(Long id) {
        return converter.mapFromUserToReadUser(userRepository.findById(id));
    }

    public List<ReadUserResponse> readAllUsers() {
        return converter.mapFromUserToReadUser(userRepository.findAll());
    }

    public ReadUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        final User currentUser = applicationContext.getCurrentUser();
        currentUser.setFirstName(updateUserRequest.getFirstName());
        currentUser.setLastName(updateUserRequest.getLastName());
        userRepository.save(currentUser);
        return converter.mapFromUserToReadUser(Optional.of(currentUser)).get();
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
