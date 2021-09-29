package com.cout.shop.model.dao.impl;

public enum SQLQuery {
    GET_USER("SELECT * FROM users JOIN role ON role.id = role_id JOIN user_status ON user_status.id = user_status_id WHERE users.login = (?)"),
    GET_ALL_USER("SELECT * FROM users JOIN role ON role.id = role_id JOIN user_status ON user_status.id = user_status_id"),
    GET_USER_BY_ID("SELECT * FROM users JOIN role ON role.id = role_id JOIN user_status ON user_status.id = user_status_id WHERE users.id = (?)"),
    INSERT_USER("INSERT INTO users (id, login, email, password, role_id, user_status_id) VALUES (DEFAULT, (?), (?), (?), (?), (?))"),
    DELETE_USER("DELETE FROM users WHERE id = (?) AND login = (?) AND password = (?)"),
    UPDATE_USER("UPDATE users SET email = (?), password = (?), role_id = (?), user_status_id = (?) WHERE users.login = (?)"),

    INSERT_CATEGORY("INSERT INTO category (id, category_name) VALUES (DEFAULT, (?))"),
    GET_CATEGORY("SELECT * FROM category WHERE category.id = (?)"),
    GET_CATEGORY_BY_NAME("SELECT * FROM category WHERE category_name = (?)"),
    GET_ALL_CATEGORY("SELECT * FROM category"),
    DELETE_CATEGORY("DELETE FROM category WHERE category.id = (?)"),
    UPDATE_CATEGORY("UPDATE category SET category_name = (?) WHERE category.id = (?)"),

    INSERT_PRODUCT("INSERT INTO product (id, name, count, price, color, category_id) VALUES (DEFAULT, (?), (?), (?), (?), (?))"),
    GET_ALL_PRODUCTS("SELECT * FROM product JOIN category ON category.id = category_id"),
    GET_PRODUCTS("SELECT * FROM product LEFT JOIN category ON category.id = category_id WHERE product.id = (?)"),
    DELETE_PRODUCT("DELETE FROM product WHERE product.id = (?)"),
    UPDATE_PRODUCT("UPDATE product SET name = (?), count = (?), price = (?), color = (?), category_id = (?) WHERE product.id = (?)"),

    INSERT_RECEIPT("INSERT INTO receipt (id, total, status_id, users_id) VALUES (DEFAULT, 0, (?), (?))"),
    GET_RECEIPT("SELECT * FROM receipt JOIN status ON status.id = status_id JOIN users ON users.id = users_id WHERE receipt.id = (?)"),
    GET_ALL_RECEIPTS("SELECT * FROM receipt JOIN status ON status.id = status_id JOIN users ON users.id = users_id "),
    GET_RECEIPT_BY_USER("SELECT * FROM receipt JOIN status ON status.id = status_id JOIN users ON users.id = users_id WHERE receipt.users_id = (?)"),
    GET_OPEN_RECEIPT("SELECT * FROM receipt JOIN status ON status.id = status_id JOIN users ON users.id = users_id WHERE users_id = (?) and status_id = 1"),
    UPDATE_RECEIPT("UPDATE receipt SET status_id = (?) WHERE receipt.id = (?)"),
    DELETE_RECEIPT("DELETE FROM receipt WHERE receipt.id = (?)"),

    INSERT_PRODUCT_TO_RECEIPT("INSERT INTO receipt_has_product (id, receipt_id, product_id, count, price) VALUES (DEFAULT, (?), (?), (?), (?))"),
    GET_ALL_PRODUCTS_IN_RECEIPTS("SELECT * FROM receipt_has_product JOIN receipt ON receipt.id = receipt_id JOIN product ON product.id = product_id"),
    GET_PRODUCTS_FROM_RECEIPTS("SELECT * FROM receipt_has_product JOIN receipt ON receipt.id = receipt_id JOIN product " +
                                                                           "ON product.id = product_id WHERE receipt_has_product.id = (?)"),
    DELETE_PRODUCT_FROM_RECEIPT("DELETE FROM receipt_has_product WHERE receipt_has_product.id = (?)");



    public String QUERY;
    SQLQuery(String QUERY){
        this.QUERY = QUERY;
    }


}
