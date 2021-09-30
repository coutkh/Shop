package com.cout.shop.model.dao.impl;

import com.cout.shop.model.dao.CategoryDao;
import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.entity.Category;
import com.cout.shop.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDaoImpl implements CategoryDao {
    private static final Logger logger = LogManager.getLogger(CategoryDaoImpl.class.getName());
    private static final CategoryDaoImpl instance = new CategoryDaoImpl();
    Connection connection = ConnectionPool.INSTANCE.getConnection();

    private CategoryDaoImpl(){}

    public static CategoryDaoImpl getInstance(){
        return instance;
    }

    @Override
    public boolean add(String name){
        boolean isSuccessful = false;
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.INSERT_CATEGORY.QUERY)) {
            statement.setString(1, name);
            statement.executeUpdate();
            isSuccessful = true;
        } catch (SQLException se) {
            logger.error("Error inserting category " + name, se);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isSuccessful;
    }

    @Override
    public Optional<Category> getCategoriesById(int id){
        Optional<Category> category = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_CATEGORY.QUERY)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                category = Optional.of(getCategoryFromRS(rs));
            }
        } catch (SQLException se) {
            logger.error("Error getting category data", se);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }

        return category;
    }
    @Override
    public Category getCategoryByName(String nameCategory){
        Category category = null;
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_CATEGORY_BY_NAME.QUERY)) {
            statement.setString(1, nameCategory);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                category = getCategoryFromRS(rs);
            }
        } catch (SQLException se) {
            logger.error("Error getting category data", se);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return category;
    }
    @Override
    public void deleteCategoriesById(Optional<Category> category){
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_CATEGORY.QUERY)){
            if(category.isPresent()) {
                statement.setInt(1, category.get().getId());
                statement.executeUpdate();
            }else {
                logger.error("Method \"deleteUserByLogin\" did not receive a parameter");
            }

        } catch (SQLException se) {
            logger.error("Error deletion category from DB", se);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }

    @Override
    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_CATEGORY.QUERY)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category category = (getCategoryFromRS(rs));
                categories.add(category);
            }
        } catch (SQLException se) {
            logger.error("Error getting category data", se);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return categories;
    }

    @Override
    public void updateCategories(Optional<Category> category){
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.UPDATE_CATEGORY.QUERY)){
            if(category.isPresent()) {
                statement.setString(1, category.get().getName());
                statement.setInt(2, category.get().getId());
                statement.executeUpdate();
            }else {
                logger.error("Method \"deleteUserByLogin\" did not receive a parameter");
            }
        } catch (SQLException se) {
            logger.error("Error of updating a category in the DB", se);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }

    private Category getCategoryFromRS(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("category_name"));
        return category;
    }
}
