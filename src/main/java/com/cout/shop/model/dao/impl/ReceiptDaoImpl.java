package com.cout.shop.model.dao.impl;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ReceiptDao;
import com.cout.shop.model.dao.UserDao;
import com.cout.shop.model.entity.*;
import com.cout.shop.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceiptDaoImpl implements ReceiptDao {
    private static final Logger logger = LogManager.getLogger(ReceiptHasProductDaoImpl.class);
    private static final ReceiptDaoImpl instance = new ReceiptDaoImpl();
    Connection connection = ConnectionPool.INSTANCE.getConnection();
    private static final UserDao userDao = UserDaoImpl.getInstance();

    public static ReceiptDaoImpl getInstance() {
        return instance;
    }


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
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }

        return allReceipts;
    }

    @Override
    public Receipt getReceipt(int id) throws DaoException {
        Receipt receipt = null;
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.GET_RECEIPT.QUERY)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                receipt = (getReceiptFromRS(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error getting product from DB", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return receipt;
    }

    @Override
    public int getIdOpenReceipt(int id) throws DaoException {
        //Optional<Receipt> receipt = Optional.empty();
        int receiptId = 0;
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.GET_OPEN_RECEIPT.QUERY)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //receipt = Optional.of(getReceiptFromRS(rs));
                receiptId = rs.getInt("id");
            } else {
                receiptId = -1;
            }
        } catch (SQLException e) {
            throw new DaoException("Error getting product from DB", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return receiptId;
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
    public void deleteReceipt(Connection conn, int id) throws DaoException {
        try (PreparedStatement ps = conn.prepareStatement(SQLQuery.DELETE_RECEIPT.QUERY)) {
            if (0 < id) {
                ps.setInt(1, id);
                ps.executeUpdate();
            } else {
                logger.error("Method \"deleteReceipt\" did not receive a parameter");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }

    @Override
    public void updateReceipt(Receipt receipt) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.UPDATE_RECEIPT.QUERY)) {
            if (receipt != null) {
                int k = 1;
                ps.setInt(k++, receipt.getStatus().getId());
                ps.setInt(k++, receipt.getId());
                ps.executeUpdate();
            } else {
                logger.error("Method \"updateReceipt\" did not receive a parameter");
            }
        } catch (SQLException e) {
            throw new DaoException("Error updating receipt in DB", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }

    private Receipt getReceiptFromRS(ResultSet rs) throws SQLException, DaoException {
        Receipt receipt = new Receipt();
        receipt.setId(rs.getInt("id"));
        receipt.setTotal(rs.getInt("total"));
        String status_name = rs.getString("status.status_name");
        receipt.setStatus(ReceiptStatus.valueOf(status_name.toUpperCase()));
        int userId = rs.getInt("receipt.users_id");
        receipt.setUser(userDao.getUserById(userId));
        receipt.setCreateDate(rs.getTimestamp("create_date"));
        receipt.setUpdateDate(rs.getTimestamp("update_date"));
        return receipt;
    }
}
