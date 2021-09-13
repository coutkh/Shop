package com.cout.shop.model.dao.impl;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ReceiptDao;
import com.cout.shop.model.entity.*;
import com.cout.shop.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceiptDaoImpl implements ReceiptDao {
    private static final ReceiptDaoImpl instance = new ReceiptDaoImpl();
    Connection connection = ConnectionPool.INSTANCE.getConnection();


    @Override
    public int add(Optional<User> user) throws DaoException {
        int key = -1;
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.INSERT_RECEIPT.QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, 1);
            if (user.isPresent()) {
                ps.setInt(5, user.get().getId());
            }
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error inserting receipt ", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }

        return key;
    }

    @Override
    public List<Receipt> getAllReceipts() {
        List<Receipt> allReceipt = new ArrayList<>();


        return null;
    }

    @Override
    public Optional<Receipt> getReceipt(Optional<User> user) throws DaoException {
        Optional<Receipt> receipt = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.GET_RECEIPT.QUERY)) {
            ps.setInt(1, Integer.parseInt("id"));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                receipt = Optional.of(getReceiptFromRS(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error getting product from DB", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return receipt;
    }

        @Override
    public void deleteReceipt(Optional<Receipt> receipt) throws DaoException {

    }

    @Override
    public void updateReceipt(Optional<Receipt> receipt) throws DaoException {

    }

    private Receipt getReceiptFromRS(ResultSet rs) throws SQLException {
        Receipt receipt = new Receipt();
        receipt.setId(rs.getInt("id"));
        receipt.setTotal(rs.getInt("total"));
//        receipt.setStatus(RecriptStatus.valueOf());
//        user.setRole(UserRole.valueOf(role.toUpperCase()));
//        receipt.setUser(new User(rs.getInt("user_id"), rs.getString("category_name")));
        receipt.setCreate_date(rs.getTimestamp("create_date"));
        receipt.setUpdate_date(rs.getTimestamp("last_update"));
    return null;
    }
}
