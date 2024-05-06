package com.thefirstone.first.controller;

import com.thefirstone.first.configuration.JwtUtil;
import com.thefirstone.first.dto.CreateUserRequest;
import com.thefirstone.first.dto.LoginRequest;
import com.thefirstone.first.dto.LoginResponse;
import com.thefirstone.first.model.User;
import com.thefirstone.first.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Inject
    private UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> registerUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        final String email = authentication.getName();
        final Optional<User> user = Optional.ofNullable(userService.findByEmail(email));
        return user.map(value -> new ResponseEntity<>(new LoginResponse(value.getFirstName(), value.getLastName(), value.getEmail(), value.getRole(), jwtUtil.createToken(value)), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
