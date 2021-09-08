package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.impl.CategoryDaoImpl;
import com.cout.shop.model.entity.Category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToProductsCommand extends Command {
    private static final CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();
    public ToProductsCommand() {
        super.commandName = "TO_PRODUCTS";
    }
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Category> categoryList = null;
        try {
            categoryList = categoryDao.getAllCategories();
        } catch (DaoException e) {
            e.printStackTrace();
        }

        session.setAttribute(SessionAttribute.CATEGORY_LIST, categoryList);
        return PagePath.ADMIN_CATEGORY_PAGE;
    }
}
