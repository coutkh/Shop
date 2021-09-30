package com.cout.shop.model.dao;

import com.cout.shop.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean add( String email , String login, String password, String role);
    List<User> getAllUsers();
    Optional<User> getUserByLogin(String login);
    void deleteUserByLogin(Optional<User> user);
    void updateUser(Optional<User> user);
    User getUserById(int id);
}
