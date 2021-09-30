package com.cout.shop.model.dao;

import com.cout.shop.model.entity.Category;
import com.cout.shop.model.entity.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    boolean addProduct(String name, int count, int price, String color, Category category);
    List<Product> getAllProducts();
    Product getProductById(int id);
    void deleteProductById(Product product);
    Product getProductById(Connection connection, int productId);
    void updateProductById(Connection connection, Product product) ;
    void updateProduct(Product product);
}

