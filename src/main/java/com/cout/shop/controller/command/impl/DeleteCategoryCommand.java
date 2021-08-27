package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.CategoryDao;
import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.impl.CategoryDaoImpl;
import com.cout.shop.model.entity.Category;
import com.cout.shop.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class DeleteCategoryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final CategoryDao categoryDao = CategoryDaoImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        Optional<Category> category = null;
        try {
            category = categoryDao.getCategoriesById(id);
        } catch (DaoException e) {
            logger.error("An error occurred when trying to read category from the database ",e);
        }
        HttpSession session = request.getSession();

        try {
            categoryDao.deleteCategoriesById(category);
        } catch (DaoException e) {
            logger.error("An error occurred when trying to delete a category from the database ",e);
        }

        List<Category> categoryList = categoryDao.getAllCategories();
        session.setAttribute(SessionAttribute.USER_LIST, userList);

        return null;
    }
}
