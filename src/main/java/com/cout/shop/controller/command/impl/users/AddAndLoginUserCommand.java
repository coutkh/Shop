package com.cout.shop.controller.command.impl.users;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestAttribute;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.service.UserService;
import com.cout.shop.model.service.impl.UserServiceImpl;
import com.cout.shop.util.TypeRe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddAndLoginUserCommand extends Command {
    private static final Logger logger = LogManager.getLogger(AddAndLoginUserCommand.class.getName());
    private static final UserService userService = UserServiceImpl.getInstance();
    public AddAndLoginUserCommand() {
        super.commandName = "ADD_AND_LOGIN_USER";
    }
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
                    page = TypeRe.redirect(PagePath.REDIRECT_SIGN_IN_PAGE);
                } else {
                    page = PagePath.SIGN_UP_PAGE;

                    request.setAttribute(RequestAttribute.SIGN_UP_ERROR, "signup.incorrectSignup");
                }
            } catch (Exception e) {
                logger.error("Error creating user", e);
                page = (String)session.getAttribute(SessionAttribute.CURRENT_PAGE);
            }
        }else {
            page = PagePath.SIGN_UP_PAGE;
        }
        return page;
    }
}
