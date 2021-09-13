package com.cout.shop.model.dao;

import com.cout.shop.model.entity.Receipt;
import com.cout.shop.model.entity.User;


import java.util.List;
import java.util.Optional;

public interface ReceiptDao {
    int add(Optional<User> user) throws DaoException;
    List<Receipt> getAllReceipts();
    Optional<Receipt> getReceipt(Optional<User> user) throws DaoException;
    void deleteReceipt(Optional<Receipt> receipt) throws DaoException;
    void updateReceipt(Optional<Receipt> receipt) throws DaoException;
}
