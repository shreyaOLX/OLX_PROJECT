package com.olx.inventories.service;

import com.olx.inventories.model.User;
import com.olx.inventories.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
