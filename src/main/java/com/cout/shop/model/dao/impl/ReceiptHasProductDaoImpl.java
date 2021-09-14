package com.cout.shop.model.dao.impl;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ReceiptHasProductDao;
import com.cout.shop.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReceiptHasProductDaoImpl implements ReceiptHasProductDao {
    private static final ReceiptHasProductDaoImpl instance = new ReceiptHasProductDaoImpl();
    Connection connection = ConnectionPool.INSTANCE.getConnection();

    private ReceiptHasProductDaoImpl() {
    }
    public static ReceiptHasProductDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addProductToReceipt(int productId, int receiptId, int count, int price) throws DaoException {
        boolean isSuccessful = false;

        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.INSERT_PRODUCT_TO_RECEIPT.QUERY)) {
            ps.setInt(1, productId);
            ps.setInt(2, receiptId);
            ps.setInt(3, count);
            ps.setInt(3, price);
            ps.executeUpdate();
            isSuccessful = true;
        } catch (SQLException e) {
            throw new DaoException("Error inserting user ", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isSuccessful;
    }
}
