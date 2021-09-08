package com.cout.shop.model.dao.impl;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ProductDao;
import com.cout.shop.model.entity.Category;
import com.cout.shop.model.entity.Product;
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

public class ProductDaoImpl implements ProductDao {
    private static final Logger logger = LogManager.getLogger(ProductDaoImpl.class);
    private static final ProductDaoImpl instance = new ProductDaoImpl();
    Connection connection = ConnectionPool.INSTANCE.getConnection();

    private ProductDaoImpl() {
    }

    public static ProductDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addProduct(String name, int count, int price, String color, Optional<Category> category) throws DaoException {
        boolean isSuccessful = false;
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.INSERT_PRODUCT.QUERY)) {
            ps.setString(1, name);
            ps.setInt(2, count);
            ps.setInt(3, price);
            ps.setString(4, color);
            if (category.isPresent()) {
                ps.setInt(5, category.get().getId());
            }
            ps.executeUpdate();
            isSuccessful = true;
        } catch (SQLException e) {
            throw new DaoException("Error inserting user " + name, e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }

        return isSuccessful;
    }

    @Override
    public List<Product> getAllProducts() throws DaoException {
        List<Product> allProducts = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.GET_ALL_PRODUCTS.QUERY)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = getProductFromRS(rs);
                allProducts.add(product);
            }
        } catch (SQLException e) {
            throw new DaoException("Error getting products from DB", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return allProducts;
    }

    @Override
    public Optional<Product> getProductById(int id) throws DaoException {
        Optional<Product> product = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.GET_PRODUCTS.QUERY)) {
            ps.setInt(1, Integer.parseInt("id"));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = Optional.of(getProductFromRS(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error getting product from DB", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return product;
    }

    @Override
    public void deleteProductById(Optional<Product> product) throws DaoException {

        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.DELETE_PRODUCT.QUERY)) {
            if (product.isPresent()) {
                ps.setInt(1, product.get().getId());
                ps.executeUpdate();
            } else {
                logger.error("Method \"deleteProductById\" did not receive a parameter");
            }
        } catch (SQLException e) {
            throw new DaoException("Error deletion product from DB", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }

    @Override
    public void updateProductById(Optional<Product> product) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.UPDATE_PRODUCT.QUERY)) {
            if (product.isPresent()){
                ps.setString(1, product.get().getName());
                ps.setInt(2, product.get().getCount());
                ps.setInt(3, product.get().getPrice());
                ps.setString(4, product.get().getColor());
                ps.setInt(5, product.get().getCategory().getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error updating product from DB", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }


    private Product getProductFromRS(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setCount(rs.getInt("count"));
        product.setPrice(rs.getInt("price"));
        product.setColor(rs.getString("color"));
        product.setCategory(new Category(rs.getInt("id"), rs.getString("name")));
        product.setCreateDate(rs.getTimestamp("create_date"));
        product.setLastUpdate(rs.getTimestamp("last_update"));

        return product;
    }


}
