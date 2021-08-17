package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestAttribute;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.service.UserService;
import com.cout.shop.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        HttpSession session = request.getSession();

        Optional<User> user = userService.authorizeUser(login, password);
        if (user.isPresent()) {

            session.setAttribute(SessionAttribute.CURRENT_USER, user.get());
            session.setAttribute(SessionAttribute.ROLE, user.get().getRole().getRole());

            /*String role1 = (String) session.getAttribute(SessionAttribute.ROLE);
            System.out.println("3333"+role1);

            String role2 = (String) session.getAttribute("role");
            System.out.println("4444"+role2);
*/
            page = PagePath.MAIN;
        } else {
            page = PagePath.SIGN_IN;
            request.setAttribute(RequestAttribute.SIGN_IN_ERROR, "signup.incorrectSignin");
        }
        return page;
    }
}
