package com.cout.shop.controller.command.impl.products;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ProductDao;
import com.cout.shop.model.dao.impl.ProductDaoImpl;
import com.cout.shop.model.entity.Product;
import com.cout.shop.util.TypeRe;

import javax.servlet.http.HttpServletRequest;

public class DeleteProductCommand extends Command {
    private static final ProductDao productDao = ProductDaoImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        Product product;
        try {
            product = productDao.getProductById(id);
            productDao.deleteProductById(product);
            page = TypeRe.redirect(PagePath.REDIRECT_PRODUCT_PAGE);
        } catch (DaoException e) {
            page = PagePath.ERROR404;
            e.printStackTrace();
        }
        return page;
    }
}
