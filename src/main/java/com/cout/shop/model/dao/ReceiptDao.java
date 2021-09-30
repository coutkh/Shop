package com.cout.shop.model.dao;

import com.cout.shop.model.entity.Receipt;
import com.cout.shop.model.entity.User;


import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ReceiptDao {

    int add(int userId);
    List<Receipt> getAllReceipts();
    Receipt getReceipt(int id);
    int getIdOpenReceipt(int id);

    List<Receipt> getReceiptByUser(int userId) throws DaoException;

    void deleteReceipt(Connection connection, int id);
    void updateReceipt(Receipt receipt);
}
