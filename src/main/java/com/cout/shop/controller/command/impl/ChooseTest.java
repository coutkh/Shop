package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ChooseTest implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        //int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        return PagePath.MAIN_PAGE;
    }
}
