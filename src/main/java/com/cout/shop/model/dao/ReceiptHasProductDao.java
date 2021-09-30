package com.cout.shop.model.dao;

import com.cout.shop.model.entity.ReceiptHasProduct;

import java.sql.Connection;
import java.util.List;

public interface ReceiptHasProductDao {

    List<ReceiptHasProduct> getAllProductsInReceipt();

    void deleteProductFromReceipt(Connection connection, int id);

    boolean addProductToReceipt(Connection connection, int productId, int receiptId, int productCount, int productPrice);

    ReceiptHasProduct getProductFromReceipt(int id);
}

