package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ToSignUpCommand extends Command {
    public ToSignUpCommand() {
        super.commandName = "TO_SIGN_UP";
    }
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.SIGN_UP_PAGE;
    }
}
