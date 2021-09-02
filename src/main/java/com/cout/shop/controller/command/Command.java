package com.cout.shop.controller.command;

import javax.servlet.http.HttpServletRequest;

public abstract class Command {

    public String getCommandName() {
        return commandName;
    }

    public String commandName="";

    public abstract String execute(HttpServletRequest request);
}