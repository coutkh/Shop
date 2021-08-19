package com.cout.shop.model.dao;

import com.cout.shop.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean add(int role_id, String email , String login, String password);
    List<User> getAllUsers();
    Optional<User> getUserByLogin(String login);
    void deleteUserByLogin(Optional<User> user);

    Optional<User> getByEmail(String login);
    Optional<User> getById(int id);
}
