package com.cout.shop.controller;

public class PagePath {
    //public static final String HOME = "/index.jsp";
    public static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
    public static final String SIGN_IN_PAGE = "/WEB-INF/jsp/signIn.jsp";
    public static final String SIGN_UP_PAGE = "/WEB-INF/jsp/signUp.jsp";

    public static final String ADMIN_PAGE_PAGE = "/WEB-INF/jsp/adminPage.jsp";
    public static final String ADMIN_USERS_PAGE = "/WEB-INF/jsp/adminUsers.jsp";
    public static final String ADMIN_CATEGORY_PAGE = "/WEB-INF/jsp/adminCategoryPage.jsp";
    public static final String TO_ADD_USER_PAGE = "/WEB-INF/jsp/adminAddUser.jsp";
    public static final String TO_EDIT_USER_PAGE = "/WEB-INF/jsp/adminEditUser.jsp";

    public static final String PRODUCTS_PAGE = "/WEB-INF/jsp/productsPage.jsp";
    public static final String TO_EDIT_PRODUCT_PAGE = "/WEB-INF/jsp/productEditPage.jsp";
    public static final String CONFIRM_SELECTION_PAGE = "/WEB-INF/jsp/confirmSelectionPage.jsp";

    public static final String TO_BASKET_PAGE = "/WEB-INF/jsp/basketPage.jsp";
    public static final String TO_ORDERS_PAGE = "/WEB-INF/jsp/ordersPage.jsp";


    public static final String ERROR404 = "/404.jsp";

    public static final String REDIRECT_SIGN_IN_PAGE = "/controller?command=to_sign_in";
    public static final String REDIRECT_ADMIN_USERS_PAGE = "/controller?command=to_admin_users";
    public static final String REDIRECT_ADMIN_CATEGORY_PAGE = "/controller?command=to_categories";
    public static final String REDIRECT_PRODUCT_PAGE = "/controller?command=to_products";
    public static final String REDIRECT_TO_BASKET_PAGE = "/controller?command=to_basket_page";
    public static final String REDIRECT_TO_ORDERS_PAGE = "/controller?command=to_orders";


    private PagePath(){}
}

