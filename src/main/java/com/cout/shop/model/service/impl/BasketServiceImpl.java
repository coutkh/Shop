package com.cout.shop.model.service.impl;

import com.cout.shop.controller.RequestParameter;
import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ProductDao;
import com.cout.shop.model.dao.ReceiptDao;
import com.cout.shop.model.dao.ReceiptHasProductDao;
import com.cout.shop.model.dao.impl.ProductDaoImpl;
import com.cout.shop.model.dao.impl.ReceiptDaoImpl;
import com.cout.shop.model.dao.impl.ReceiptHasProductDaoImpl;
import com.cout.shop.model.entity.Product;
import com.cout.shop.model.entity.ReceiptHasProduct;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.exception.ServiceException;
import com.cout.shop.model.service.BasketService;
import com.cout.shop.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BasketServiceImpl implements BasketService {
    Connection connection = ConnectionPool.INSTANCE.getConnection();
    private static final BasketServiceImpl instance = new BasketServiceImpl();
    private static final ReceiptHasProductDao receiptDao = ReceiptHasProductDaoImpl.getInstance();
    private static final ProductDao productDao = ProductDaoImpl.getInstance();
    private static final ReceiptDao receipt = ReceiptDaoImpl.getInstance();
    private static final BasketService service = BasketServiceImpl.getInstance();


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

        try {
            connection.setAutoCommit(false);
            int id = ReceiptDaoImpl.getInstance().getIdOpenReceipt(userId);
            if(0 < id){
                receiptId = id;
            }else {
                receiptId = ReceiptDaoImpl.getInstance().add(userId);
            }
            Product product = productDao.getProductById(connection, productId);
            int tempAmount = product.getCount();
            tempAmount = tempAmount - productCount;
            product.setCount(tempAmount);
            productDao.updateProductById(connection, product);
            isAdd = receiptDao.addProductToReceipt(connection, productId, receiptId, productCount, productPrice);

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new ServiceException(e);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }

        return isAdd;
    }

    @Override
    public void deleteProductFromBasket(ReceiptHasProduct rp) throws SQLException {
        try {
            connection.setAutoCommit(false);
            Product product = productDao.getProductById(connection, rp.getProduct().getId());
            int tempAmount = product.getCount();
            tempAmount = tempAmount + rp.getCount();
            product.setCount(tempAmount);
            productDao.updateProductById(connection, product);
            receiptDao.deleteProductFromReceipt(connection, rp.getId());

            connection.commit();
        } catch (SQLException | DaoException throwables) {
            connection.rollback();
            throwables.printStackTrace();
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }
    @Override
    public void deleteProductFromBasket(Connection conn, ReceiptHasProduct rp) {
        try {
            //conn.setAutoCommit(false);
            Product product = productDao.getProductById(conn, rp.getProduct().getId());
            int tempAmount = product.getCount();
            tempAmount = tempAmount + rp.getCount();
            product.setCount(tempAmount);
            productDao.updateProductById(conn, product);
            receiptDao.deleteProductFromReceipt(conn, rp.getId());

            //conn.commit();
        } catch (DaoException throwables) {
            //conn.rollback();
            throwables.printStackTrace();
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(conn);
        }
    }

    @Override
    public void deleteReceipt(int id) throws SQLException {
        try {
            connection.setAutoCommit(false);

                List<ReceiptHasProduct> listProducrts = receiptDao.getAllProductsInReceipt();
                for(ReceiptHasProduct list : listProducrts){
                    if(id == list.getReceipt().getId()){
                        service.deleteProductFromBasket(connection, list);
                    }
                }
            receipt.deleteReceipt(connection, id);
            connection.commit();
        } catch (SQLException | DaoException throwables) {
            connection.rollback();
            throwables.printStackTrace();
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }

}
