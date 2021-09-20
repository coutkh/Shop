package com.cout.shop.model.dao;

import com.cout.shop.model.entity.Category;
import com.cout.shop.model.entity.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    boolean addProduct(String name, int count, int price, String color, Category category) throws DaoException;

    List<Product> getAllProducts() throws DaoException;

    Product getProductById(int id) throws DaoException;

    void deleteProductById(Product product) throws DaoException;

    //void updateProductById(Product product) throws DaoException;

    Product getProductById(Connection connection, int productId) throws DaoException;

    void updateProductById(Connection connection, Product product) throws DaoException;

    //void updateProductById(Product product) throws DaoException;

    void updateProduct(Product product) throws DaoException;
}

