package com.cout.shop.controller.command;

import com.cout.shop.controller.command.impl.*;

public enum CommandType {

    TO_MAIN ,
    CHANGE_LOCALE,
    TO_SIGN_IN,
    SIGN_IN,
    LOGOUT,
    TO_ADMIN_PAGE,
    TO_ADMIN_USERS,
    DELETE_USER,
    TO_ADD_USER_PAGE,
    ADD_USER,
    TO_EDIT_USER_PAGE,
    EDIT_USER,
    TO_SIGN_UP,
    SIGN_UP,
    ADD_AND_LOGIN_USER,
    TO_PRODUCTS_PAGE,
    TO_CATEGORIES,
    DELETE_CATEGORY,
    ADD_CATEGORY,
    EDIT_CATEGORY,
    CHOOSE_TEST,
    ADD_PRODUCT,
    TO_PRODUCTS,
    TO_CONFIRM_SELECTION_PAGE,
    CONFIRM_AND_BACK,
    CONFIRM_GO_TO_BASKET,
    TO_BASKET_PAGE,
    DELETE_PRODUCT_FROM_RECEIPT,
    DELETE_RECEIPT,
    DELETE_PRODUCT,
    TO_EDIT_PRODUCT_PAGE,
    EDIT_PRODUCT;

    Command command;
    public Command getCommand(){
        return command;
    }
}
