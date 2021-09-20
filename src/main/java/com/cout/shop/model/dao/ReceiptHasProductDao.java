package com.cout.shop.model.dao;

import com.cout.shop.model.entity.Product;
import com.cout.shop.model.entity.Receipt;
import com.cout.shop.model.entity.ReceiptHasProduct;

import java.sql.Connection;
import java.util.List;

public interface ReceiptHasProductDao {
    //boolean addProductToReceipt(int productId, int receiptId, int count, int price) throws DaoException;

    List<ReceiptHasProduct> getAllProductsInReceipt();

    void deleteProductFromReceipt(Connection connection, int id);

    boolean addProductToReceipt(Connection connection, int productId, int receiptId, int productCount, int productPrice) throws DaoException;

    ReceiptHasProduct getProductFromReceipt(int id);
}

