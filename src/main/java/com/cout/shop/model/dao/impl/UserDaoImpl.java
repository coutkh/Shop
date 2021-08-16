package com.cout.shop.model.dao.impl;

import com.cout.shop.model.dao.UserDao;
import com.cout.shop.model.entity.User;
import com.cout.shop.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {}
    public static UserDaoImpl getInstance(){ return instance; }

    @Override
    public boolean add(int role_id, String login, String email, String password) {

        boolean isSuccessful = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.INSERT_USER.QUERY);)
        {
            statement.setInt(1, role_id);
            statement.setString(2, email);
            statement.setString(3, login);
            statement.setString(4, password);

            statement.executeUpdate();
            isSuccessful = true;
        } catch(SQLException e){
            throw new DaoException("Error inserting user " + login, e);
        }
        return isSuccessful;;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getByEmail(String login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getById(int id) {
        return Optional.empty();
    }
}
