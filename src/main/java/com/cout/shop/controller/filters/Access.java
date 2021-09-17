package com.cout.shop.controller.filters;

import com.cout.shop.controller.command.Command;
import com.cout.shop.controller.command.CommandBuilder;
import com.cout.shop.controller.command.CommandType;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.cout.shop.controller.command.CommandType.*;

public enum Access {
    GUEST(Stream.of(
            TO_MAIN,
            CHANGE_LOCALE,
            SIGN_IN,
            TO_SIGN_IN,
            SIGN_UP,
            TO_SIGN_UP,
            ADD_AND_LOGIN_USER,
            TO_PRODUCTS_PAGE,
            CHOOSE_TEST,
            TO_PRODUCTS,
            TO_CONFIRM_SELECTION_PAGE

    ).map(CommandBuilder::getCommand).collect(Collectors.toSet())),

    USER(Stream.of(
            TO_MAIN,
            CHANGE_LOCALE,
            SIGN_IN,
            TO_SIGN_IN,
            LOGOUT,
            SIGN_UP,
            TO_SIGN_UP,
            ADD_AND_LOGIN_USER,
            TO_PRODUCTS_PAGE,
            CHOOSE_TEST,
            TO_PRODUCTS,
            TO_CONFIRM_SELECTION_PAGE,
            CONFIRM_AND_BACK,
            CONFIRM_GO_TO_BASKET,
            TO_BASKET_PAGE,
            DELETE_PRODUCT_FROM_RECEIPT

    ).map(CommandBuilder::getCommand).collect(Collectors.toSet())),

    ADMIN(Stream.of(
            TO_MAIN,
            CHANGE_LOCALE,
            SIGN_IN,
            TO_SIGN_IN,
            LOGOUT,
            TO_ADMIN_PAGE,
            TO_ADMIN_USERS,
            DELETE_USER,
            TO_ADD_USER_PAGE,
            ADD_USER,
            TO_EDIT_USER_PAGE,
            EDIT_USER,
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
            DELETE_PRODUCT_FROM_RECEIPT
    ).map(CommandBuilder::getCommand).collect(Collectors.toSet()));

    private final Set<Command> commands;

    Access(Set<Command> commands){
        this.commands = commands;
    }
    public Set<Command> getCommands(){
        return Collections.unmodifiableSet(commands);
    }
}
