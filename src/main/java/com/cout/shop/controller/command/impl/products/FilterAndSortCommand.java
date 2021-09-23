package com.cout.shop.controller.command.impl.products;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilterAndSortCommand extends Command {
    private static final ProductDaoImpl productDaoImpl = ProductDaoImpl.getInstance();
    private static final CategoryDaoImpl categoryDaoImpl = CategoryDaoImpl.getInstance();
    public FilterAndSortCommand() {
        super.commandName = "FILTERS_AND_SORT";
    }
    @Override
    public String execute(HttpServletRequest request) {
        List<String> list = Arrays.asList(request.getParameterValues("categoryName"));
        String sort = request.getParameter("sort");
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
        List<Product> filterProductList;
        if(0< list.size()){
             filterProductList = productList.stream().filter(it -> list.contains(it.getCategory().getName())).collect(Collectors.toList());
        }else {
            filterProductList = productList;
        }
        List<Product> resultProductList;
        if("from_cheap".equals(sort)){
            resultProductList = filterProductList.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
        } else if ("from_expensive".equals(sort)){
            resultProductList = filterProductList.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList());
        } else if ("color".equals(sort)){
        resultProductList = filterProductList.stream().sorted(Comparator.comparing(Product::getColor)).collect(Collectors.toList());
        } else if ("from_lot".equals(sort)){
            resultProductList = filterProductList.stream().sorted(Comparator.comparing(Product::getCount)).collect(Collectors.toList());
        } else if ("from_top".equals(sort)){
            resultProductList = filterProductList.stream().sorted(Comparator.comparing(Product::getCount).reversed()).collect(Collectors.toList());
        } else {
            resultProductList = filterProductList;
        }
        session.setAttribute(SessionAttribute.CATEGORY_LIST, categoryList);
        session.setAttribute(SessionAttribute.PRODUCT_LIST, resultProductList);
        return PagePath.PRODUCTS_PAGE;
    }
}
