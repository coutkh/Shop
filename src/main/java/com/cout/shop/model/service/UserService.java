package com.cout.shop.model.service;

import com.cout.shop.model.entity.User;
import com.cout.shop.model.entity.UserRole;
import com.cout.shop.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean createUser(String login, String password, String email, String role) throws ServiceException;
    Optional<User> authorizeUser(String login, String password);
    List<User> getAllUsers();

    void updateUser(String login, String password, String email, UserRole role) throws ServiceException;
}

