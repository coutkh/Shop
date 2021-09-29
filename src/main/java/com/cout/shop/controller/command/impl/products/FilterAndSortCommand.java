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
import java.util.*;
import java.util.stream.Collectors;

public class FilterAndSortCommand extends Command {
    private static final ProductDaoImpl productDaoImpl = ProductDaoImpl.getInstance();
    private static final CategoryDaoImpl categoryDaoImpl = CategoryDaoImpl.getInstance();
    public FilterAndSortCommand() {
        super.commandName = "FILTERS_AND_SORT";
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

        String sort = request.getParameter("sort");

        List<Product> filterCategoryProductList;
        if(null != request.getParameterValues("categoryName")){
            List<String> finalCategoryList = Arrays.asList(request.getParameterValues("categoryName"));
            filterCategoryProductList = productList.stream().filter(it -> finalCategoryList.contains(it.getCategory().getName())).collect(Collectors.toList());
        }else {
            filterCategoryProductList = productList;
        }
        List<Product> filterColorProductList;
        if(null != request.getParameterValues("color")){
            List<String> colorList = Arrays.asList(request.getParameterValues("color"));
            filterColorProductList = filterCategoryProductList .stream().filter(it -> colorList.contains(it.getColor())).collect(Collectors.toList());
        }else {
            filterColorProductList = filterCategoryProductList ;
        }
        List<Product> filterRangeProductList;
        if(null != request.getParameter("min") || null != request.getParameter("max")){
            double min;
            double max;
            if(!"".equals(request.getParameter("min"))){
                 min = Double.parseDouble(request.getParameter("min"));
            }else {min = 0;}
            if(!"".equals(request.getParameter("max"))){
                max = Double.parseDouble(request.getParameter("max"));
            }else {max = Double.MAX_VALUE;}
            filterRangeProductList = filterColorProductList .stream().filter(it -> it.getPrice() >= min && it.getPrice() <= max).collect(Collectors.toList());
        }else {
            filterRangeProductList = filterColorProductList ;
        }

        List<Product> filterProductList = filterRangeProductList;
        List<Product> resultProductList;
        if("from_cheap".equals(sort)){
            resultProductList = filterProductList.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
        } else if ("from_expensive".equals(sort)){
            resultProductList = filterProductList.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList());
        } else if ("new".equals(sort)){
            resultProductList = filterProductList.stream().sorted(Comparator.comparing(Product::getCreateDate).reversed()).collect(Collectors.toList());
        } else {
            resultProductList = filterProductList;
        }
        session.setAttribute(SessionAttribute.CATEGORY_LIST, categoryList);
        session.setAttribute(SessionAttribute.PRODUCT_LIST, resultProductList);
        return PagePath.PRODUCTS_PAGE;
    }
}
