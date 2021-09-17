package com.cout.shop.controller.command.impl.users;

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

public class SignInCommand extends Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();

    public SignInCommand() {
        super.commandName = "SIGN_IN";
    }

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
            page = PagePath.MAIN_PAGE;
        } else {
            page = PagePath.SIGN_IN_PAGE;
            request.setAttribute(RequestAttribute.SIGN_IN_ERROR, "signup.incorrectSignin");
        }
        return page;
    }
}
