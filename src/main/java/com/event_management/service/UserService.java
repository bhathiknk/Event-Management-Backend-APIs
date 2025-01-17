package com.event_management.service;


import com.event_management.model.User;

import com.event_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> signIn(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }
}
