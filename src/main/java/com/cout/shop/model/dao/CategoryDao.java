package com.cout.shop.model.dao;

import com.cout.shop.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    boolean add( String name) ;
    List<Category> getAllCategories();
    Optional<Category> getCategoriesById(int id);

    Category getCategoryByName(String nameCategory);

    void deleteCategoriesById(Optional<Category> category);
    void updateCategories(Optional<Category> category);
}
