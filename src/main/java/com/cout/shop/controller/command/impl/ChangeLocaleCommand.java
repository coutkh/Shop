package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand extends Command {

    public ChangeLocaleCommand() {
        super.commandName = "CHANGE_LOCALE";
    }

    @Override
    public String execute(HttpServletRequest request) {
        String locale = request.getParameter(RequestParameter.NEW_LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.LOCALE, locale);
        return session.getAttribute(SessionAttribute.CURRENT_PAGE).toString();
    }
}
