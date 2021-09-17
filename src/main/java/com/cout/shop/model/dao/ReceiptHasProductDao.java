package com.cout.shop.model.dao;

import com.cout.shop.model.entity.Product;
import com.cout.shop.model.entity.ReceiptHasProduct;

import java.util.List;

public interface ReceiptHasProductDao {
    boolean addProductToReceipt(int productId, int receiptId, int count, int price) throws DaoException;

    List<ReceiptHasProduct> getAllProductsInReceipt();

    void deleteProductFromReceipt(int id);
}

