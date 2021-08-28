package com.cout.shop.controller.command.impl;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class DeleteUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final UserService userService = UserServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(RequestParameter.LOGIN);
        Optional<User> user = Optional.empty();
        try {
            user = userDao.getUserByLogin(login);
        } catch (DaoException e) {
            logger.error("An error occurred when trying to read a user from the database ",e);
        }
        HttpSession session = request.getSession();

        try {
            userDao.deleteUserByLogin(user);
        } catch (DaoException e) {
            logger.error("An error occurred when trying to delete a user from the database ",e);
        }

        List<User> userList = userService.getAllUsers();
        session.setAttribute(SessionAttribute.USER_LIST, userList);

        return PagePath.ADMIN_USERS_PAGE;

    }
}
