package com.cout.shop.controller.command;

import com.cout.shop.controller.command.impl.*;

public enum CommandType {
    /*TO_MAIN                 {{ this.command = new ToMainPageCommand(); }},
    CHANGE_LOCALE           {{ this.command = new ChangeLocaleCommand();}},
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
    SIGN_UP                 {{ this.command = new (); }},
    ADD_AND_LOGIN_USER      {{ this.command = new ();}},
    TO_PRODUCTS_PAGE        {{ this.command = new ();}},
    TO_CATEGORIES           {{ this.command = new ();}},
    DELETE_CATEGORY         {{ this.command = new ();}},
    ADD_CATEGORY            {{ this.command = new ();}},
    EDIT_CATEGORY           {{ this.command = new ();}},
    CHOOSE_TEST             {{ this.command = new ();}};*/

    TO_MAIN ,
    CHANGE_LOCALE,
    TO_SIGN_IN,
    SIGN_IN,
    LOGOUT,
    TO_ADMIN_PAGE,
    TO_ADMIN_USERS,
    DELETE_USER,
    TO_ADD_USER_PAGE,
    ADD_USER,
    TO_EDIT_USER_PAGE,
    EDIT_USER,
    TO_SIGN_UP,
    SIGN_UP,
    ADD_AND_LOGIN_USER,
    TO_PRODUCTS_PAGE,
    TO_CATEGORIES,
    DELETE_CATEGORY,
    ADD_CATEGORY,
    EDIT_CATEGORY,
    CHOOSE_TEST,
    ADD_PRODUCT,
    TO_PRODUCTS,
    TO_CONFIRM_SELECTION_PAGE;

    Command command;
    public Command getCommand(){
        return command;
    }
}
