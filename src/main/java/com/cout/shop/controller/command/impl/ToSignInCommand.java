package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ToSignInCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.SIGN_IN;
    }
}
