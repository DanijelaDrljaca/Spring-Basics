package com.thefirstone.first.controller;

import com.thefirstone.first.dto.ReadUserResponse;
import com.thefirstone.first.dto.UpdateUserRequest;
import com.thefirstone.first.exception.NotFoundException;
import com.thefirstone.first.model.Address;
import com.thefirstone.first.model.User;
import com.thefirstone.first.service.UserService;
import com.thefirstone.first.util.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private ApplicationContext applicationContext;

    @GetMapping(value = "/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReadUserResponse> readUser(@PathVariable Long id) {
        final Optional<ReadUserResponse> byId = userService.readUser(id);
        return byId.map(readUserResponse -> new ResponseEntity<>(readUserResponse, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReadUserResponse>> readAllUsers() {
        return new ResponseEntity<>(userService.readAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ReadUserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return new ResponseEntity<>(userService.updateUser(updateUserRequest), HttpStatus.OK);
    }
}
