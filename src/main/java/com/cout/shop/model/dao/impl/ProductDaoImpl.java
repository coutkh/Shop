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
    private static final Logger logger = LogManager.getLogger(ProductDaoImpl.class.getName());
    private static final ProductDaoImpl instance = new ProductDaoImpl();
    Connection connection = ConnectionPool.INSTANCE.getConnection();

    private ProductDaoImpl() {
    }

    public static ProductDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addProduct(String name, int count, int price, String color, Category category) {
        boolean isSuccessful = false;
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.INSERT_PRODUCT.QUERY)) {
            ps.setString(1, name);
            ps.setInt(2, count);
            ps.setInt(3, price);
            ps.setString(4, color);
            ps.setInt(5, category.getId());
            ps.executeUpdate();
            isSuccessful = true;
        } catch (SQLException se) {
            logger.error("Error inserting product " + name, se);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }

        return isSuccessful;
    }

    @Override
    public List<Product> getAllProducts(){
        List<Product> allProducts = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.GET_ALL_PRODUCTS.QUERY)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = getProductFromRS(rs);
                allProducts.add(product);
            }
        } catch (SQLException se) {
            logger.error("Error getting products from DB", se);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return allProducts;
    }

    @Override
    public Product getProductById(int id){
        Product product = null;
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.GET_PRODUCTS.QUERY)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = getProductFromRS(rs);
            }
        } catch (SQLException se) {
            logger.error("Error getting product from DB by id", se);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return product;
    }

    @Override
    public Product getProductById(Connection conn, int id){
        Product product = null;
        try (PreparedStatement ps = conn.prepareStatement(SQLQuery.GET_PRODUCTS.QUERY)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = getProductFromRS(rs);
            }
        } catch (SQLException se) {
            logger.error("Error getting product from DB", se);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(conn);
        }
        return product;
    }

    @Override
    public void deleteProductById(Product product){

        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.DELETE_PRODUCT.QUERY)) {
            if (product != null) {
                ps.setInt(1, product.getId());
                ps.executeUpdate();
            } else {
                logger.error("Method \"deleteProductById\" did not receive a parameter");
            }
        } catch (SQLException e) {
            logger.error("Error deletion product from DB by id", e);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }

    @Override
    public void updateProductById(Connection conn, Product product){
        try (PreparedStatement ps = conn.prepareStatement(SQLQuery.UPDATE_PRODUCT.QUERY)) {
            if (product != null) {
                int k = 1;
                ps.setString(k++, product.getName());
                ps.setInt(k++, product.getCount());
                ps.setInt(k++, product.getPrice());
                ps.setString(k++, product.getColor());
                ps.setInt(k++, product.getCategory().getId());
                ps.setInt(k++, product.getId());
                ps.executeUpdate();
            }
        } catch (SQLException se) {
            logger.error("Error updating product from DB by id", se);
        } finally {
            ConnectionPool.INSTANCE.releaseConnection(conn);
        }
    }

    @Override
    public void updateProduct(Product product){
        try (PreparedStatement ps = connection.prepareStatement(SQLQuery.UPDATE_PRODUCT.QUERY)) {
            if (product != null) {
                int k = 1;
                ps.setString(k++, product.getName());
                ps.setInt(k++, product.getCount());
                ps.setInt(k++, product.getPrice());
                ps.setString(k++, product.getColor());
                ps.setInt(k++, product.getCategory().getId());
                ps.setInt(k++, product.getId());
                ps.executeUpdate();
            }
        } catch (SQLException se) {
            logger.error("Error updating product from DB", se);
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
        product.setCategory(new Category(rs.getInt("category_id"), rs.getString("category_name")));
        product.setCreateDate(rs.getTimestamp("create_date"));
        product.setLastUpdate(rs.getTimestamp("last_update"));

        return product;
    }


}
