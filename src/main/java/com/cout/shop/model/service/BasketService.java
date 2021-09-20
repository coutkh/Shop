package com.cout.shop.model.service;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.entity.ReceiptHasProduct;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public interface BasketService {

    boolean addProductToBasket(int productId, int productCount, int productPrice, User user) throws DaoException, SQLException, ServiceException;

    void deleteProductFromBasket(ReceiptHasProduct receipt) throws SQLException;

    void deleteProductFromBasket(Connection conn, ReceiptHasProduct receipt) throws SQLException;

    void deleteReceipt(int id) throws SQLException;

    //boolean addProductToBasket(Connection connection, int productId, int productCount, int productPrice, User user) throws DaoException, SQLException, ServiceException;
}
