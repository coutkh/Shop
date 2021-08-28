package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.entity.UserRole;
import com.cout.shop.model.service.UserService;
import com.cout.shop.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EditUserCommand implements Command {
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();

        String login = request.getParameter(RequestParameter.LOGIN);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String role = request.getParameter(RequestParameter.ROLE);
        if(!"admin".equals(role)){
            role = "user";
        }
        try {
            userService.updateUser(login, email, password, UserRole.valueOf(role.toUpperCase()));
            page = PagePath.REDIRECT_ADMIN_USERS_PAGE;

            } catch (Exception e) {
            e.printStackTrace();
            page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
            //error massage
        }
        return page;
    }
}
