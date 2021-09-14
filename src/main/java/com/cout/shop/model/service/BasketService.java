package com.cout.shop.model.service;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.exception.ServiceException;

import java.sql.SQLException;

public interface BasketService {

    boolean addProductToBasket(int productId, int productCount, int productPrice, User user) throws DaoException, SQLException, ServiceException;
}
