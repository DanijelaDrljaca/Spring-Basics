package com.thefirstone.first.util;

import com.thefirstone.first.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ApplicationContext {

    private User currentUser;

    public ApplicationContext() {
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
