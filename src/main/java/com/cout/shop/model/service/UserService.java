package com.cout.shop.model.service;

import com.cout.shop.model.entity.User;
import com.cout.shop.model.entity.UserRole;
import com.cout.shop.model.entity.UserStatus;
import com.cout.shop.model.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean createUser(String login, String password, String email, String role) throws ServiceException, SQLException;
    Optional<User> authorizeUser(String login, String password);
    List<User> getAllUsers();

    void updateUser(String login, String password, String email, UserRole role, UserStatus userStatus) throws ServiceException;
}

