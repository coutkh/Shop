package com.cout.shop.model.dao;

import com.cout.shop.model.entity.Category;
import com.cout.shop.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    boolean addProduct(String name, int count, int price, String color, Optional<Category> category) throws DaoException;

    List<Product> getAllProducts() throws DaoException;

    Product getProductById(int id) throws DaoException;

    //void deleteProductById(Optional<Product> product) throws DaoException;

    void deleteProductById(Product product) throws DaoException;

    void updateProductById(Product product) throws DaoException;
}
