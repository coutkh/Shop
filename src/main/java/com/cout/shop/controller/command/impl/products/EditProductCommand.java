package com.cout.shop.controller.command.impl.products;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.CategoryDao;
import com.cout.shop.model.dao.ProductDao;
import com.cout.shop.model.dao.impl.CategoryDaoImpl;
import com.cout.shop.model.dao.impl.ProductDaoImpl;
import com.cout.shop.model.entity.Category;
import com.cout.shop.model.entity.Product;
import com.cout.shop.util.TypeRe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EditProductCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ToEditProductPageCommand.class.getName());
    private static final CategoryDao categoryDao = CategoryDaoImpl.getInstance();
    private static final ProductDao productDao = ProductDaoImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        String page;

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
            logger.error("Error getting category by name", e);
        }
        Product product = new Product(id, name, count, price, color, category);
        productDao.updateProduct(product);
        page = TypeRe.redirect(PagePath.REDIRECT_PRODUCT_PAGE);
        return page;
    }
}
