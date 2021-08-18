package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestAttribute;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ToAdminPageCommand implements Command {
    public static final Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String page;

        String role = (String) session.getAttribute(SessionAttribute.ROLE);

        if (role != null && role.equals(UserRole.ADMIN.getRole())) {
            page = PagePath.ADMIN_PAGE;
        } else {
            page = PagePath.SIGN_IN;
            request.setAttribute(RequestAttribute.SIGN_IN_ERROR, "signup.incorrectSignin");
        }
        return page;
    }
}
