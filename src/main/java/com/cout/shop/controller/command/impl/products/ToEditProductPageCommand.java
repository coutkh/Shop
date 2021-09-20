package com.cout.shop.controller.command.impl.products;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.CategoryDao;
import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.impl.CategoryDaoImpl;
import com.cout.shop.model.entity.Category;
import com.cout.shop.model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToEditProductPageCommand extends Command {
    private static final CategoryDao categoryDao = CategoryDaoImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();

        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        String name = request.getParameter(RequestParameter.NAME);
        int count = Integer.parseInt(request.getParameter(RequestParameter.COUNT));
        int price = Integer.parseInt(request.getParameter(RequestParameter.PRICE));
        String color = request.getParameter(RequestParameter.COLOR);
        String nameCategory = request.getParameter(RequestParameter.CATEGORY_NAME);
        Category category = null;
        try {
            category = categoryDao.getCategoryByName(nameCategory);
        } catch (Exception e) {
            e.printStackTrace();
            //page = (String)session.getAttribute(SessionAttribute.CURRENT_PAGE);
        }
        Product product = new Product(id, name, count, price, color, category);
        session.setAttribute("product", product);
        List<Category> categoryList = null;

        try {
            categoryList = categoryDao.getAllCategories();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        session.setAttribute(SessionAttribute.CATEGORY_LIST, categoryList);

        return PagePath.TO_EDIT_PRODUCT_PAGE;
    }
}
