package com.cout.shop.controller.command.impl.admin;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ProductDao;
import com.cout.shop.model.dao.impl.CategoryDaoImpl;
import com.cout.shop.model.dao.impl.ProductDaoImpl;
import com.cout.shop.model.entity.Category;
import com.cout.shop.util.TypeRe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AddProductCommand extends Command {
    private static final ProductDao product = ProductDaoImpl.getInstance();
    private static final CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();

    public AddProductCommand() {
        super.commandName = "ADD_PRODUCT";
    }
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();

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

        try {
            boolean isProductAdd = product.addProduct(name, count, price, color, category);
            if (isProductAdd) {
                page = TypeRe.redirect(PagePath.REDIRECT_PRODUCT_PAGE);
            } else {
                page = PagePath.ERROR404;

                //request.setAttribute(RequestAttribute.SIGN_UP_ERROR, "signup.incorrectSignup");
            }
        } catch (DaoException e) {
            e.printStackTrace();
            page = (String)session.getAttribute(SessionAttribute.CURRENT_PAGE);
        }
        return page;
    }
}
