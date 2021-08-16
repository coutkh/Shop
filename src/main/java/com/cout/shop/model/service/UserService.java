package com.cout.shop.model.service;

import com.cout.shop.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean createUser(String login, String password, String email);
    Optional<User> authorizeUser(String login, String password);
    List<User> getAllUsers();
}
