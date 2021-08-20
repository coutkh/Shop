package com.cout.shop.model.dao.impl;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.UserDao;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.entity.UserRole;
import com.cout.shop.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(String login, String email, String password, String role) throws DaoException {
        int roleId;
        boolean isSuccessful = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.INSERT_USER.QUERY)) {
            statement.setString(1, login);
            statement.setString(2, email);
            statement.setString(3, password);
            if ("admin".equals(role)) {
                roleId = 3;
            } else {
                roleId = 2;
            }
            statement.setInt(4, roleId);

            statement.executeUpdate();
            isSuccessful = true;
        } catch (SQLException e) {
            throw new DaoException("Error inserting user " + login, e);
        }
        return isSuccessful;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_USER.QUERY)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = (getUserFromRS(rs));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public Optional<User> getUserByLogin(String login) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_USER.QUERY)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = Optional.of(getUserFromRS(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error getting user data", e);
        }
        return user;
    }

    @Override
    public void deleteUserByLogin(Optional<User> user) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_USER.QUERY)) {
            if (user.isPresent()) {
                statement.setInt(1, user.get().getId());
                statement.setString(2, user.get().getLogin());
                statement.setString(3, user.get().getPassword());
                statement.executeUpdate();
            } else {
                logger.error("Method \"deleteUserByLogin\" did not receive a parameter");
            }
        } catch (SQLException e) {
            throw new DaoException("Error deletion user from DB");
        }
    }

    @Override
    public void updateUser(Optional<User> user) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UPDATE_USER.QUERY)){
            if (user.isPresent()){
                statement.setString(1, user.get().getEmail());
                statement.setString(2, user.get().getPassword());
                statement.setInt(3, user.get().getRole().getId());
                statement.setString(4, user.get().getLogin());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            throw new DaoException("Error updating user from DB");
        }
    }

    private User getUserFromRS(ResultSet rs) throws SQLException {
        User user = new User();

        int id = rs.getInt("id");
        String login = rs.getString("login");
        String email = rs.getString("email");
        String password = rs.getString("password");
        Timestamp createTime = rs.getTimestamp("create_time");
        int roleId = rs.getInt("role_id");
        String role;
        if (3 == roleId) {
            role = "admin";
        } else {
            role = "user";
        }

        user.setId(id);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreateTime(createTime);
        user.setRole(UserRole.valueOf(role.toUpperCase()));
        return user;
    }
}
