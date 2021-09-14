package com.cout.shop.model.dao.impl;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ReceiptDao;
import com.cout.shop.model.dao.UserDao;
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
    private static final UserDao userDao = UserDaoImpl.getInstance();

    public static ReceiptDaoImpl getInstance(){return instance;}


    @Override
    public int add(int userId) throws DaoException {
        int key = -1;
        int statusId = 1;
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.INSERT_RECEIPT.QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, statusId);
            ps.setInt(2, userId);
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
        List<Receipt> allReceipts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_RECEIPTS.QUERY)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Receipt receipt = (getReceiptFromRS(rs));
                allReceipts.add(receipt);
            }
        } catch (SQLException | DaoException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }

        return allReceipts;
    }

    @Override
    public Optional<Receipt> getReceipt(int id) throws DaoException {
        Optional<Receipt> receipt = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.GET_RECEIPT.QUERY)) {
            ps.setInt(1, id);
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
    public List<Receipt> getReceiptByUser(int userId) throws DaoException {
        List<Receipt> allReceipts = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.GET_RECEIPT_BY_USER.QUERY)) {
                ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Receipt receipt = getReceiptFromRS(rs);
                allReceipts.add(receipt);
            }
        } catch (SQLException e) {
            throw new DaoException("Error getting receipt from DB", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return allReceipts;
    }

        @Override
    public void deleteReceipt(Optional<Receipt> receipt) throws DaoException {

    }

    @Override
    public void updateReceipt(Optional<Receipt> receipt) throws DaoException {

    }

    private Receipt getReceiptFromRS(ResultSet rs) throws SQLException, DaoException {
        Receipt receipt = new Receipt();
        receipt.setId(rs.getInt("id"));
        receipt.setTotal(rs.getInt("total"));
        //receipt.setStatus(ReceiptStatus.valueOf(String.valueOf(rs.getInt("id"))));
        String status_name = rs.getString("status.status_name");
        receipt.setStatus(ReceiptStatus.valueOf(status_name.toUpperCase()));
        int userId = rs.getInt("receipt.users_id");
        receipt.setUser(userDao.getUserById(Integer.parseInt("receipt.users_id")));
        receipt.setCreateDate(rs.getTimestamp("create_date"));
        receipt.setUpdateDate(rs.getTimestamp("last_update"));
    return receipt;
    }
}
