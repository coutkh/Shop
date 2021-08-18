package com.cout.shop.model.dao.impl;

import com.cout.shop.model.dao.UserDao;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.entity.UserRole;
import com.cout.shop.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
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
            //throw new DaoException("Error inserting user " + login, e);
            e.printStackTrace();
        }
        return isSuccessful;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_USER.QUERY)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                User user = (getUserFromRS(rs));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public Optional<User> getByLogin(String login) {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_USER.QUERY)){
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                user = Optional.of(getUserFromRS(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public Optional<User> getByEmail(String login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getById(int id) {
        return Optional.empty();
    }

    private User getUserFromRS(ResultSet rs) throws SQLException {
        User user = new User();

        int id = rs.getInt("id");
        String login = rs.getString("login");
        String email = rs.getString("email");
        String password = rs.getString("password");
        Timestamp createTime = rs.getTimestamp("create_time");
        String role = rs.getString("login");

        user.setId(id);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreateTime(createTime);
        user.setRole(UserRole.valueOf(role.toUpperCase()));
        return user;
    }
}
