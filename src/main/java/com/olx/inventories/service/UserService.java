package com.olx.inventories.service;

import com.olx.inventories.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
