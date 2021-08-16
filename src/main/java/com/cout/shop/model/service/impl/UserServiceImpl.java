package com.cout.shop.model.service.impl;

import com.cout.shop.model.entity.User;
import com.cout.shop.model.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public boolean createUser(String login, String password, String email) {
        return false;
    }

    @Override
    public Optional<User> authorizeUser(String login, String password) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
