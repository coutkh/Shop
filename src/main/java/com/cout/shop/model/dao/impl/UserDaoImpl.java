package com.cout.shop.model.dao.impl;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.UserDao;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.entity.UserRole;
import com.cout.shop.model.entity.UserStatus;
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
    Connection connection = ConnectionPool.INSTANCE.getConnection();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(String login, String email, String password, String role) throws DaoException {
        int roleId;
        boolean isSuccessful = false;
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.INSERT_USER.QUERY)) {
            int k = 1;
            statement.setString(k++, login);
            statement.setString(k++, email);
            statement.setString(k++, password);
            if ("admin".equals(role)) {
                roleId = 3;
            } else {
                roleId = 2;
            }
            statement.setInt(k++, roleId);
            statement.setInt(k++, 1);

            statement.executeUpdate();
            isSuccessful = true;
        } catch (SQLException e) {
            throw new DaoException("Error inserting user " + login, e);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isSuccessful;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_USER.QUERY)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = (getUserFromRS(rs));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return userList;
    }

    @Override
    public Optional<User> getUserByLogin(String login) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_USER.QUERY)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = Optional.of(getUserFromRS(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error getting user data", e);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public User getUserById(int id) throws DaoException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_USER_BY_ID.QUERY)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = getUserFromRS(rs);
            }
        } catch (SQLException e) {
            throw new DaoException("Error getting user data", e);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public void deleteUserByLogin(Optional<User> user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_USER.QUERY)) {
            if (user.isPresent()) {
                int k = 1;
                statement.setInt(k++, user.get().getId());
                statement.setString(k++, user.get().getLogin());
                statement.setString(k++, user.get().getPassword());
                statement.executeUpdate();
            } else {
                logger.error("Method \"deleteUserByLogin\" did not receive a parameter");
            }
        } catch (SQLException e) {
            throw new DaoException("Error deletion user from DB", e);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }

    @Override
    public void updateUser(Optional<User> user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.UPDATE_USER.QUERY)){
            if (user.isPresent()){
                int k = 1;
                statement.setString(k++, user.get().getEmail());
                statement.setString(k++, user.get().getPassword());
                statement.setInt(k++, user.get().getRole().getId());
                statement.setInt(k++, user.get().getUserStatus().getId());
                statement.setString(k++, user.get().getLogin());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            throw new DaoException("Error updating user from DB", e);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
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
        String userStatus = rs.getString("user_status.name");

        user.setId(id);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreateTime(createTime);
        user.setRole(UserRole.valueOf(role.toUpperCase()));
        user.setUserStatus(UserStatus.valueOf(userStatus.toUpperCase()));
        return user;
    }
}
