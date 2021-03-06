package com.cout.shop.controller.command.impl.admin;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.entity.UserRole;
import com.cout.shop.model.entity.UserStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToEditUserPageCommand extends Command {
    public ToEditUserPageCommand() {
        super.commandName = "TO_EDIT_USER_PAGE";
    }
    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String email = request.getParameter(RequestParameter.EMAIL);
        String role = request.getParameter(RequestParameter.ROLE);
        String userStatus = request.getParameter(RequestParameter.USER_STATUS);

        HttpSession session = request.getSession();

        User user = new User(login , email, password, UserRole.valueOf(role.toUpperCase()), UserStatus.valueOf(userStatus.toUpperCase()));
        session.setAttribute("user", user);

        return PagePath.TO_EDIT_USER_PAGE;
    }
}
