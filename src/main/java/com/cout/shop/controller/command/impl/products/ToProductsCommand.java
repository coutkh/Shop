package com.cout.shop.controller.command.impl.products;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.impl.CategoryDaoImpl;
import com.cout.shop.model.dao.impl.ProductDaoImpl;
import com.cout.shop.model.entity.Category;
import com.cout.shop.model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToProductsCommand extends Command {
    private static final ProductDaoImpl productDaoImpl = ProductDaoImpl.getInstance();
    private static final CategoryDaoImpl categoryDaoImpl = CategoryDaoImpl.getInstance();
    public ToProductsCommand() {
        super.commandName = "TO_PRODUCTS";
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Category> categoryList = categoryDaoImpl.getAllCategories();
        List<Product> productList = productDaoImpl.getAllProducts();

        session.setAttribute(SessionAttribute.CATEGORY_LIST, categoryList);
        session.setAttribute(SessionAttribute.PRODUCT_LIST, productList);
        return PagePath.PRODUCTS_PAGE;
    }
}
