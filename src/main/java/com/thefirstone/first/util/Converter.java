package com.thefirstone.first.util;

import com.thefirstone.first.dto.ReadUserResponse;
import com.thefirstone.first.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Converter {

    public Optional<ReadUserResponse> mapFromUserToReadUser(Optional<User> user) {
        return user.map(value -> new ReadUserResponse(value.getFirstName(), value.getLastName(), value.getEmail()));
    }

    public List<ReadUserResponse> mapFromUserToReadUser(List<User> users) {
        return users.stream().map(user -> mapFromUserToReadUser(Optional.of(user)).get()).collect(Collectors.toList());
    }
}
