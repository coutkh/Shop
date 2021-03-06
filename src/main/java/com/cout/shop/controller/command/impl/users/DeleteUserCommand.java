package com.cout.shop.controller.command.impl.users;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.UserDao;
import com.cout.shop.model.dao.impl.UserDaoImpl;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.service.UserService;
import com.cout.shop.model.service.impl.UserServiceImpl;
import com.cout.shop.util.TypeRe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class DeleteUserCommand extends Command {
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final UserService userService = UserServiceImpl.getInstance();
    public DeleteUserCommand() {
        super.commandName = "DELETE_USER";
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(RequestParameter.LOGIN);
        Optional<User> user = Optional.empty();
        user = userDao.getUserByLogin(login);
        HttpSession session = request.getSession();

        userDao.deleteUserByLogin(user);

        List<User> userList = userService.getAllUsers();
        session.setAttribute(SessionAttribute.USER_LIST, userList);

        return TypeRe.redirect(PagePath.REDIRECT_ADMIN_USERS_PAGE);

    }
}
