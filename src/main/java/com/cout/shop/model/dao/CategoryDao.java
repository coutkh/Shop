package com.cout.shop.model.dao;

import com.cout.shop.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    boolean add( String name) throws DaoException;
    List<Category> getAllCategories() throws DaoException;
    Optional<Category> getCategoriesById(int id) throws DaoException;
    void deleteCategoriesById(Optional<Category> category) throws DaoException;
    void updateCategories(Optional<Category> category) throws DaoException;
}
