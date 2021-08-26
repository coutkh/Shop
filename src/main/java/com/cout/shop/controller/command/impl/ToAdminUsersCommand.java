package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.service.UserService;
import com.cout.shop.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAdminUsersCommand implements Command {

    private static final UserService userService = UserServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request)    {
        HttpSession session = request.getSession();

        List<User> userList = userService.getAllUsers();

        session.setAttribute(SessionAttribute.USER_LIST, userList);
        return PagePath.ADMIN_USERS_PAGE;
    }
}
