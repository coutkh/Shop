package com.cout.shop.model.dao;

public interface ReceiptHasProductDao {
    boolean addProductToReceipt(int productId, int receiptId, int count, int price) throws DaoException;
}
