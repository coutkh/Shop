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
    private static final Logger logger = LogManager.getLogger();
    private static final CategoryDaoImpl instance = new CategoryDaoImpl();
    Connection connection = ConnectionPool.INSTANCE.getConnection();

    private CategoryDaoImpl(){}

    public static CategoryDaoImpl getInstance(){
        return instance;
    }

    @Override
    public boolean add(String name) throws DaoException {
        boolean isSuccessful = false;
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.INSERT_CATEGORY.QUERY)) {
            statement.setString(1, name);
            statement.executeUpdate();
            isSuccessful = true;
        } catch (SQLException e) {
            throw new DaoException("Error inserting category " + name, e);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isSuccessful;
    }

    @Override
    public Optional<Category> getCategoriesById(int id) throws DaoException {
        Optional<Category> category = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_CATEGORY.QUERY)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                category = Optional.of(getCategoryFromRS(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error getting category data", e);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }

        return category;
    }
    public Optional<Category> getCategoryByName(String nameCategory) throws DaoException {
        Optional<Category> category = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_CATEGORY_BY_NAME.QUERY)) {
            statement.setString(1, nameCategory);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                category = Optional.of(getCategoryFromRS(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error getting category data", e);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }

        return category;
    }
    @Override
    public void deleteCategoriesById(Optional<Category> category) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_CATEGORY.QUERY)){
            if(category.isPresent()) {
                statement.setInt(1, category.get().getId());
                statement.executeUpdate();
            }else {
                logger.error("Method \"deleteUserByLogin\" did not receive a parameter");
            }

        } catch (SQLException e) {
            throw new DaoException("Error deletion category from DB");
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }

    @Override
    public List<Category> getAllCategories() throws DaoException {
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_CATEGORY.QUERY)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category category = (getCategoryFromRS(rs));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new DaoException("Error getting category data", e);
        }finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return categories;
    }

    @Override
    public void updateCategories(Optional<Category> category) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQLQuery.UPDATE_CATEGORY.QUERY)){
            if(category.isPresent()) {
                statement.setString(1, category.get().getName());
                statement.setInt(2, category.get().getId());
                statement.executeUpdate();
            }else {
                logger.error("Method \"deleteUserByLogin\" did not receive a parameter");
            }
        } catch (SQLException e) {
            throw new DaoException("Error of updating a category in the DB");
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
