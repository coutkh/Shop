package com.cout.shop.controller.command;


import com.cout.shop.controller.command.impl.*;

public enum CommandType {
    TO_MAIN                 {{ this.command = new ToMainPageCommand(); }},
    CHANGE_LOCALE_EN        {{ this.command = new ChangeLocaleEnCommand();}},
    CHANGE_LOCALE_RU        {{ this.command = new ChangeLocaleRuCommand();}},
    TO_SIGN_IN              {{ this.command = new ToSignInCommand(); }},
    SIGN_IN                 {{ this.command = new SignInCommand(); }},
    LOGOUT                  {{ this.command = new LogoutCommand(); }},
    TO_ADMIN_PAGE           {{ this.command = new ToAdminPageCommand(); }},
    TO_ADMIN_USERS          {{ this.command = new ToAdminUsersCommand(); }},
    DELETE_USER             {{ this.command = new DeleteUserCommand();}},
    TO_ADD_USER_PAGE        {{ this.command = new ToAddUserPageCommand();}},
    ADD_USER                {{ this.command = new AddUserCommand();}},
    TO_EDIT_USER_PAGE       {{ this.command = new ToEditUserPage();}},
    EDIT_USER               {{ this.command = new EditUserCommand();}},
    TO_SIGN_UP              {{ this.command = new ToSignUpCommand(); }},
    SIGN_UP                 {{ this.command = new SignUpCommand(); }},
    ADD_AND_LOGIN_USER      {{ this.command = new AddAndLoginUserCommand();}},
    TO_PRODUCTS_PAGE        {{ this.command = new ToProductsPageCommand();}},
    TO_CATEGORIES           {{ this.command = new ToCategoriesCommand();}},
    DELETE_CATEGORY         {{ this.command = new DeleteCategoryCommand();}};

    /*SEARCH                  {{ this.command = new SearchCommand(); }},
    DELETE_CART_ITEM        {{ this.command = new DeleteCartItemCommand(); }},
    TO_CATALOG              {{ this.command = new ToCatalogCommand(); }},
    TO_CART                 {{ this.command = new ToCartCommand(); }},
    ADD_TO_CART             {{ this.command = new AddToCartCommand(); }},
    ADD_NEW_PRODUCT         {{ this.command = new AddNewProductCommand(); }},
    TO_ADD_NEW_PRODUCT      {{ this.command = new ToAddNewProductCommand(); }},
    CHECKOUT                {{ this.command = new CheckoutCommand(); }},
    TO_PRODUCT              {{ this.command = new ToProductPageCommand(); }},
    COMMIT_REVIEW           {{ this.command = new CommitReviewCommand(); }},
    TO_PROFILE              {{ this.command = new ToProfileCommand(); }},

    TO_CHANGE_PASSWORD      {{ this.command = new ToChangePassword(); }},

    TO_ADMIN_ORDERS         {{ this.command = new ToAdminOrdersCommand(); }},
    TO_ADMIN_PRODUCTS       {{ this.command = new ToAdminProductsCommand(); }},
    TO_ADMIN_DISCOUNTS      {{ this.command = new ToAdminDiscountsCommand(); }};*/

    Command command;
    public Command getCommand(){
        return command;
    }
}
