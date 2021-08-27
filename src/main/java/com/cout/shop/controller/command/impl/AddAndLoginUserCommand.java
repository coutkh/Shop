package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestAttribute;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.service.UserService;
import com.cout.shop.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddAndLoginUserCommand implements Command {
    private static final UserService userService = UserServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();

        String login = request.getParameter(RequestParameter.LOGIN);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String rePassword = request.getParameter(RequestParameter.RE_PASSWORD);
        String role = "user";
        if(password.equals(rePassword)){
            try {
                boolean isUserCreated = userService.createUser(login, password, email, role);
                if (isUserCreated) {
                    page = PagePath.REDIRECT_SIGN_IN_PAGE;
                } else {
                    page = PagePath.SIGN_UP_PAGE;

                    request.setAttribute(RequestAttribute.SIGN_UP_ERROR, "signup.incorrectSignup");
                }
            } catch (Exception e) {
                e.printStackTrace();
                page = (String)session.getAttribute(SessionAttribute.CURRENT_PAGE);
            }
        }else {
            page = PagePath.SIGN_UP_PAGE;
        }

        return page;
    }
}