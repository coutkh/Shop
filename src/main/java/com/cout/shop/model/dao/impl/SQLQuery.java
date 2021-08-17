package com.cout.shop.model.dao.impl;

public enum SQLQuery {
    GET_USER("SELECT * FROM users LEFT JOIN role ON role.id = role_id WHERE login = (?)"),
    GET_ALL_USER("SELECT * FROM users LEFT JOIN role ON role.id = role_id"),
    INSERT_USER("INSERT INTO users (id, login, email, password) VALUES (DEFAULT, (?), (?), (?))"),
    DELETE_USER("DELETE FROM users WHERE id = (?) AND login = (?) AND password = (?)"),
    UPDATE_USER("UPDATE users SET email = (?) WHERE id = (?)");

    public String QUERY;
    SQLQuery(String QUERY){
        this.QUERY = QUERY;
    }
}