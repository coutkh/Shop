package com.cout.shop.model.dao;

import com.cout.shop.model.entity.Category;
import com.cout.shop.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    boolean addProduct(String name, int count, int price, String color, Optional<Category> category) throws DaoException;

    List<Product> getAllProducts() throws DaoException;

    Optional<Product> getProductById(int id) throws DaoException;

    void deleteProductById(Optional<Product> product) throws DaoException;

    void updateProductById(Optional<Product> product) throws DaoException;
}
