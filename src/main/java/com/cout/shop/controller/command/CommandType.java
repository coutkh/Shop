package com.cout.shop.controller.command;


import com.cout.shop.controller.command.impl.*;

public enum CommandType {
    TO_MAIN                 {{ this.command = new ToMainPageCommand(); }},
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
    SIGN_UP                 {{ this.command = new SignUpCommand(); }},
    ADD_AND_LOGIN_USER      {{ this.command = new AddAndLoginUserCommand();}},
    TO_PRODUCTS_PAGE        {{ this.command = new ToProductsPageCommand();}},
    TO_CATEGORIES           {{ this.command = new ToCategoriesCommand();}},
    DELETE_CATEGORY         {{ this.command = new DeleteCategoryCommand();}},
    ADD_CATEGORY            {{ this.command = new AddCategoryCommand();}},
    EDIT_CATEGORY           {{ this.command = new EditCategoryCommand();}},
    CHOOSE_TEST             {{ this.command = new ChooseTest();}};

    Command command;
    public Command getCommand(){
        return command;
    }
}
