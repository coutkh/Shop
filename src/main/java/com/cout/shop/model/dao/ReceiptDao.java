package com.cout.shop.model.dao;

import com.cout.shop.model.entity.Receipt;
import com.cout.shop.model.entity.User;


import java.util.List;
import java.util.Optional;

public interface ReceiptDao {
    //int add(Optional<User> user) throws DaoException;

    int add(int userId) throws DaoException;

    List<Receipt> getAllReceipts();
    Receipt getReceipt(int id) throws DaoException;

    //Optional<Receipt> getOpenReceipt(int id) throws DaoException;

    int getIdOpenReceipt(int id) throws DaoException;

    List<Receipt> getReceiptByUser(int userId) throws DaoException;

    void deleteReceipt(Optional<Receipt> receipt) throws DaoException;
    void updateReceipt(Optional<Receipt> receipt) throws DaoException;
}
