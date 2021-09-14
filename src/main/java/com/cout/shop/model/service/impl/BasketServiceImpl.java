package com.cout.shop.model.service.impl;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ReceiptHasProductDao;
import com.cout.shop.model.dao.impl.ReceiptDaoImpl;
import com.cout.shop.model.dao.impl.ReceiptHasProductDaoImpl;
import com.cout.shop.model.entity.Receipt;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.exception.ServiceException;
import com.cout.shop.model.service.BasketService;
import com.cout.shop.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasketServiceImpl implements BasketService {
    Connection connection = ConnectionPool.INSTANCE.getConnection();
    private static final BasketServiceImpl instance = new BasketServiceImpl();
    private static final ReceiptHasProductDao receiptDao = ReceiptHasProductDaoImpl.getInstance();

    private BasketServiceImpl() {
    }

    public static BasketServiceImpl getInstance() {
        return instance;
    }


    @Override
    public boolean addProductToBasket(int productId, int productCount, int productPrice, User user) throws DaoException, SQLException, ServiceException {
        boolean isAdd = false;
        int userId = 0;
        int receiptId = 0;
        if(user != null){
            userId = user.getId();
        }else {
            System.out.println("insert logger");
        }
        List<Receipt> receiptList = ReceiptDaoImpl.getInstance().getReceiptByUser(userId);
        if(0 < receiptList.size()){
            for(Receipt receipt : receiptList){
                if(1 == receipt.getStatus().getId()){
                    receiptId = receipt.getStatus().getId();
                }else {
                    receiptId = ReceiptDaoImpl.getInstance().add(userId);
                }
            }
        }else {
            receiptId = ReceiptDaoImpl.getInstance().add(userId);
        }
        try {
            connection.commit();

            isAdd = receiptDao.addProductToReceipt(productId, receiptId, productCount, productPrice);
        } catch (Exception e) {
            connection.rollback();
            throw new ServiceException(e);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }

        return isAdd;
    }

}
