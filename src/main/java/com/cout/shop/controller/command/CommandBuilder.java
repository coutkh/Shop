package com.cout.shop.controller.command;

import com.cout.shop.controller.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CommandBuilder {
    private static final Logger logger = LogManager.getLogger(CommandBuilder.class);
    private CommandBuilder(){}

    public static Command getCommand(CommandType commandType){
    Command currentCommand = null;


        if (commandType != null){
        try {
            switch (commandType) {
                case TO_MAIN: currentCommand = new ToMainPageCommand(); break;
                case CHANGE_LOCALE: currentCommand = new ChangeLocaleCommand(); break;
                case TO_SIGN_IN: currentCommand = new ToSignInCommand(); break;
                case SIGN_IN: currentCommand = new SignInCommand(); break;
                case LOGOUT: currentCommand = new LogoutCommand(); break;
                case TO_ADMIN_PAGE: currentCommand = new ToAdminPageCommand(); break;
                case TO_ADMIN_USERS: currentCommand = new ToAdminUsersCommand(); break;
                case DELETE_USER: currentCommand = new DeleteUserCommand(); break;
                case TO_ADD_USER_PAGE: currentCommand = new ToAddUserPageCommand(); break;
                case ADD_USER: currentCommand = new AddUserCommand(); break;
                case TO_EDIT_USER_PAGE: currentCommand = new ToEditUserPageCommand(); break;
                case EDIT_USER: currentCommand = new EditUserCommand(); break;
                case TO_SIGN_UP: currentCommand = new ToSignUpCommand(); break;
                case SIGN_UP: currentCommand = new SignUpCommand(); break;
                case ADD_AND_LOGIN_USER: currentCommand = new AddAndLoginUserCommand(); break;
                case TO_PRODUCTS_PAGE: currentCommand = new ToProductsPageCommand(); break;
                case TO_CATEGORIES: currentCommand = new ToCategoriesCommand(); break;
                case DELETE_CATEGORY: currentCommand = new DeleteCategoryCommand(); break;
                case ADD_CATEGORY: currentCommand = new AddCategoryCommand(); break;
                case EDIT_CATEGORY: currentCommand = new EditCategoryCommand(); break;
                case CHOOSE_TEST: currentCommand = new ChooseTest(); break;
                default:
                    break;
            }

        } catch (IllegalArgumentException e){
            logger.error("No such command found {}", commandType, e);
            currentCommand = new UnknownCommand();
        }
    } else {
        currentCommand = new UnknownCommand();
    }
        return currentCommand;
}
}
