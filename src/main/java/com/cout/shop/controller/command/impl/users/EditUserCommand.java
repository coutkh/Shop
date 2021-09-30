package com.cout.shop.controller.command.impl.users;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.impl.CategoryDaoImpl;
import com.cout.shop.model.entity.UserRole;
import com.cout.shop.model.entity.UserStatus;
import com.cout.shop.model.service.UserService;
import com.cout.shop.model.service.impl.UserServiceImpl;
import com.cout.shop.util.TypeRe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EditUserCommand extends Command {
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class.getName());
    private static final UserService userService = UserServiceImpl.getInstance();

    public EditUserCommand() {
        super.commandName = "EDIT_USER";
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();

        String login = request.getParameter(RequestParameter.LOGIN);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String role = request.getParameter(RequestParameter.ROLE);
        if (!"admin".equals(role)) {
            role = "user";
        }
        String userStatus = request.getParameter(RequestParameter.USER_STATUS);
        try {
            userService.updateUser(login, email, password, UserRole.valueOf(role.toUpperCase()), UserStatus.valueOf(userStatus.toUpperCase()));
            page = TypeRe.redirect(PagePath.REDIRECT_ADMIN_USERS_PAGE);

        } catch (Exception e) {
            logger.error("Error editing user", e);
            page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
            //error massage
        }
        return page;
    }
}
