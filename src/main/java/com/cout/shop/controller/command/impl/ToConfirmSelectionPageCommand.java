package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToConfirmSelectionPageCommand extends Command {
    public ToConfirmSelectionPageCommand() {
        super.commandName = "TO_CONFIRM_SELECTION_PAGE";
    }
    @Override
    public String execute(HttpServletRequest request) {

        String name = request.getParameter(RequestParameter.NAME);
        int count = Integer.parseInt(request.getParameter(RequestParameter.COUNT));
        int price = Integer.parseInt(request.getParameter(RequestParameter.PRICE));
        String color = request.getParameter(RequestParameter.PRICE);

        HttpSession session = request.getSession();

        Product product = new Product(name , count, price, color);
        session.setAttribute("product", product);

        return PagePath.CONFIRM_SELECTION_PAGE;

    }
}
