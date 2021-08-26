package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ToAdminPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.ADMIN_PAGE_PAGE;
    }
}
