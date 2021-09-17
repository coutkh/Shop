package com.cout.shop.controller.command.impl.users;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {
    public LogoutCommand() {
        super.commandName = "LOGOUT";
    }
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute(SessionAttribute.CURRENT_USER, null);
        session.setAttribute(SessionAttribute.ROLE, null);
        return PagePath.MAIN_PAGE;
    }
}
