package com.cout.shop.controller.command.impl.users;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ToSignInCommand extends Command {
    public ToSignInCommand() {
        super.commandName = "TO_SIGN_IN";
    }
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.SIGN_IN_PAGE;
    }
}
