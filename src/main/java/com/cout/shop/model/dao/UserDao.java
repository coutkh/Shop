package com.cout.shop.model.dao;

import com.cout.shop.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean add(int role_id, String email , String login, String password);
    List<User> getAll();
    Optional<User> getByLogin(String login);
    Optional<User> getByEmail(String login);
    Optional<User> getById(int id);
}
