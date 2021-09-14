package com.cout.shop.model.dao.impl;

public enum SQLQuery {
    GET_USER("SELECT * FROM users LEFT JOIN role ON role.id = role_id WHERE login = (?)"),
    GET_ALL_USER("SELECT * FROM users LEFT JOIN role ON role.id = role_id"),
    GET_USER_BY_ID("SELECT * FROM users LEFT JOIN role ON role.id = role_id WHERE id = (?)"),
    INSERT_USER("INSERT INTO users (id, login, email, password, role_id) VALUES (DEFAULT, (?), (?), (?), (?))"),
    DELETE_USER("DELETE FROM users WHERE id = (?) AND login = (?) AND password = (?)"),
    UPDATE_USER("UPDATE users SET email = (?), password = (?), role_id = (?) WHERE login = (?)"),

    INSERT_CATEGORY("INSERT INTO category (id, category_name) VALUES (DEFAULT, (?))"),
    GET_CATEGORY("SELECT * FROM category WHERE id = (?)"),
    GET_CATEGORY_BY_NAME("SELECT * FROM category WHERE category_name = (?)"),
    GET_ALL_CATEGORY("SELECT * FROM category"),
    DELETE_CATEGORY("DELETE FROM category WHERE id = (?)"),
    UPDATE_CATEGORY("UPDATE category SET category_name = (?) WHERE id = (?)"),

    INSERT_PRODUCT("INSERT INTO product (id, name, count, price, color, category_id) VALUES (DEFAULT, (?), (?), (?), (?), (?))"),
    GET_ALL_PRODUCTS("SELECT * FROM product LEFT JOIN category ON category.id = category_id"),
    GET_PRODUCTS("SELECT * FROM product LEFT JOIN category ON category.id = category_id WHERE id = (?)"),
    DELETE_PRODUCT("DELETE FROM product WHERE id = (?)"),
    UPDATE_PRODUCT("UPDATE product SET name = (?), count = (?), price = (?), color = (?), category_id = (?) WHERE id = (?)"),

    INSERT_RECEIPT("INSERT INTO receipt (id, total, status_id, users_id) VALUES (DEFAULT, 0, (?), (?))"),
    GET_RECEIPT("SELECT * FROM receipt JOIN status ON status.id = status_id JOIN users ON users.id = users_id WHERE id = (?)"),
    GET_ALL_RECEIPTS("SELECT * FROM receipt JOIN status ON status.id = status_id JOIN users ON users.id = users_id "),
    GET_RECEIPT_BY_USER("SELECT * FROM receipt JOIN status ON status.id = status_id JOIN users ON users.id = users_id WHERE users_id = (?)"),

    INSERT_PRODUCT_TO_RECEIPT("INSERT INTO receipt_has_product (id, receipt_id, product_id, count, price) VALUES (DEFAULT, (?), (?), (?), (?)");


    public String QUERY;
    SQLQuery(String QUERY){
        this.QUERY = QUERY;
    }


}
