package com.cout.shop.model.dao;

import com.cout.shop.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean add( String email , String login, String password, String role) throws DaoException;
    List<User> getAllUsers();
    Optional<User> getUserByLogin(String login) throws DaoException;
    void deleteUserByLogin(Optional<User> user) throws DaoException;
    void updateUser(Optional<User> user) throws DaoException;

    User getUserById(int id) throws DaoException;
}
