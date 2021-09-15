package com.cout.shop.model.dao.impl;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ProductDao;
import com.cout.shop.model.dao.ReceiptDao;
import com.cout.shop.model.dao.ReceiptHasProductDao;
import com.cout.shop.model.entity.Receipt;
import com.cout.shop.model.entity.ReceiptHasProduct;
import com.cout.shop.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptHasProductDaoImpl implements ReceiptHasProductDao {
    private static final ReceiptHasProductDaoImpl instance = new ReceiptHasProductDaoImpl();
    private static final ReceiptDao receiptDao = ReceiptDaoImpl.getInstance();
    private static final ProductDao productDao = ProductDaoImpl.getInstance();
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
            int k = 1;
            ps.setInt(k++, receiptId);
            ps.setInt(k++, productId);
            ps.setInt(k++, count);
            ps.setInt(k++, price);
            ps.executeUpdate();
            isSuccessful = true;
        } catch (SQLException e) {
            throw new DaoException("Error inserting user ", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isSuccessful;
    }

    @Override
    public List<ReceiptHasProduct> getAllProductsInReceipt() {
        List<ReceiptHasProduct> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.GET_ALL_PRODUCTS_IN_RECEIPTS.QUERY)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ReceiptHasProduct rp = getProductsReceiptFromRS(rs);
                list.add(rp);
            }
        } catch (SQLException | DaoException throwables) {
            throwables.printStackTrace();
        } finally {
        ConnectionPool.INSTANCE.releaseConnection(connection);
    }
        return list;
    }

    private ReceiptHasProduct getProductsReceiptFromRS(ResultSet rs) throws SQLException, DaoException {
        ReceiptHasProduct rp = new ReceiptHasProduct();
        rp.setId(rs.getInt("id"));
        rp.setReceipt(receiptDao.getReceipt(rs.getInt("receipt_has_product.receipt_id")));
        rp.setProduct(productDao.getProductById(rs.getInt("receipt_has_product.product_id")));
        rp.setCount(rs.getInt("count"));
        rp.setPrice(rs.getInt("price"));
        return rp;
    }
}
