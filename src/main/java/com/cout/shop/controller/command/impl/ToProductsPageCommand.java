package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ToProductsPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        return PagePath.PRODUCTS_PAGE;
    }
}
