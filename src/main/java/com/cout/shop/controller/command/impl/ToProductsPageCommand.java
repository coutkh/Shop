package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.impl.CategoryDaoImpl;
import com.cout.shop.model.dao.impl.ProductDaoImpl;
import com.cout.shop.model.entity.Category;
import com.cout.shop.model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToProductsPageCommand extends Command {
    private static final CategoryDaoImpl categoryDaoImpl = CategoryDaoImpl.getInstance();
    private static final ProductDaoImpl productDaoImpl = ProductDaoImpl.getInstance();
    public ToProductsPageCommand() {
        super.commandName = "TO_PRODUCTS_PAGE";
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Category> categoryList = null;
        try {
            categoryList = categoryDaoImpl.getAllCategories();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        List<Product> productList = null;
        try {
            productList = productDaoImpl.getAllProducts();
        } catch (DaoException e) {
            e.printStackTrace();
        }

        session.setAttribute(SessionAttribute.CATEGORY_LIST, categoryList);
        session.setAttribute(SessionAttribute.PRODUCT_LIST, productList);
        return PagePath.PRODUCTS_PAGE;
    }
}
