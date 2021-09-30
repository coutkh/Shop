package com.cout.shop.model.dao.impl;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ProductDao;
import com.cout.shop.model.dao.ReceiptDao;
import com.cout.shop.model.dao.ReceiptHasProductDao;
import com.cout.shop.model.entity.Receipt;
import com.cout.shop.model.entity.ReceiptHasProduct;
import com.cout.shop.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptHasProductDaoImpl implements ReceiptHasProductDao {
    private static final Logger logger = LogManager.getLogger(ReceiptHasProductDaoImpl.class.getName());
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
    public boolean addProductToReceipt(Connection conn, int productId, int receiptId, int count, int price){
        boolean isSuccessful = false;

        try (PreparedStatement ps = conn.prepareStatement(SQLQuery.INSERT_PRODUCT_TO_RECEIPT.QUERY)) {
            int k = 1;
            ps.setInt(k++, receiptId);
            ps.setInt(k++, productId);
            ps.setInt(k++, count);
            ps.setInt(k++, price);
            ps.executeUpdate();
            isSuccessful = true;
        } catch (SQLException se) {
            logger.error("Error inserting product to receipt ", se);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(conn);
        }
        return isSuccessful;
    }

    @Override
    public ReceiptHasProduct getProductFromReceipt(int id) {
        ReceiptHasProduct rp = null;
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.GET_PRODUCTS_FROM_RECEIPTS.QUERY)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rp = getProductsReceiptFromRS(rs);
            }
        } catch (SQLException se) {
            logger.error("Error getting product from receipt", se);
        }
        return rp;
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
        } catch (SQLException se) {
            logger.error("Error getting all products from receipt", se);
        } finally {
        ConnectionPool.INSTANCE.releaseConnection(connection);
    }
        return list;
    }
    @Override
    public void deleteProductFromReceipt(Connection conn, int id){
        try(PreparedStatement ps = conn.prepareStatement(SQLQuery.DELETE_PRODUCT_FROM_RECEIPT.QUERY)){
            if(0 < id){
                ps.setInt(1, id);
                ps.executeUpdate();
            } else {
                logger.error("Method \"deleteProductFromReceipt\" did not receive a parameter");
            }
        } catch (SQLException se) {
            logger.error("Error deleting product from receipt", se);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(conn);
        }

    }


    private ReceiptHasProduct getProductsReceiptFromRS(ResultSet rs) throws SQLException{
        ReceiptHasProduct rp = new ReceiptHasProduct();
        rp.setId(rs.getInt("id"));
        rp.setReceipt(receiptDao.getReceipt(rs.getInt("receipt_has_product.receipt_id")));
        rp.setProduct(productDao.getProductById(rs.getInt("receipt_has_product.product_id")));
        rp.setCount(rs.getInt("count"));
        rp.setPrice(rs.getInt("price"));
        return rp;
    }
}
