package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.impl.CategoryDaoImpl;
import com.cout.shop.model.entity.Category;
import com.cout.shop.util.TypeRe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class EditCategoryCommand extends Command {
    private static final CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();
    public EditCategoryCommand() {
        super.commandName = "EDIT_CATEGORY";
    }
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();

        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        String category = request.getParameter(RequestParameter.CATEGORY_NAME);
        Optional<Category> category1 = Optional.of(new Category(id, category));

        try {
            categoryDao.updateCategories(category1);
            page = TypeRe.redirect(PagePath.REDIRECT_ADMIN_CATEGORY_PAGE);
        } catch (DaoException e) {
            e.printStackTrace();
            page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        }
        return page;
    }
}
