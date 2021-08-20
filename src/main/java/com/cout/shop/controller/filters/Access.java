package com.cout.shop.controller.filters;

import com.cout.shop.controller.command.Command;
import com.cout.shop.controller.command.CommandType;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.cout.shop.controller.command.CommandType.*;

public enum Access {
    GUEST(Stream.of(
            SIGN_IN,
            TO_SIGN_IN,
            TO_MAIN
    ).map(CommandType::getCommand).collect(Collectors.toSet())),

    USER(Stream.of(
            SIGN_IN,
            TO_SIGN_IN,
            TO_MAIN,
            LOGOUT
    ).map(CommandType::getCommand).collect(Collectors.toSet())),

    ADMIN(Stream.of(
            SIGN_IN,
            TO_SIGN_IN,
            TO_MAIN,
            LOGOUT,
            TO_ADMIN_PAGE,
            TO_ADMIN_USERS,
            DELETE_USER,
            TO_ADD_USER_PAGE,
            ADD_USER,
            TO_EDIT_USER_PAGE,
            EDIT_USER
    ).map(CommandType::getCommand).collect(Collectors.toSet()));

    private final Set<Command> commands;

    Access(Set<Command> commands){
        this.commands = commands;
    }
    public Set<Command> getCommands(){
        return Collections.unmodifiableSet(commands);
    }
}
