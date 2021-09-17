package com.cout.shop.controller.command.impl.admin;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.impl.CategoryDaoImpl;
import com.cout.shop.util.TypeRe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddCategoryCommand extends Command {
    private static final Logger logger = LogManager.getLogger(AddCategoryCommand.class);
    private static final CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();
    public AddCategoryCommand() {
        super.commandName = "ADD_CATEGORY";
    }

    @Override
    public String execute(HttpServletRequest request) {

        String page;
        HttpSession session = request.getSession();
        String nameCategory = request.getParameter(RequestParameter.CATEGORY_NAME);
        try {
            boolean isUserCreated = categoryDao.add(nameCategory);
            if (isUserCreated) {
                page = PagePath.REDIRECT_ADMIN_CATEGORY_PAGE;
                //page = PagePath.ADMIN_CATEGORY_PAGE;
            } else {
                page = PagePath.ERROR404;

                //request.setAttribute(RequestAttribute.SIGN_UP_ERROR, "signup.incorrectSignup");
            }
        } catch (Exception e) {
            e.printStackTrace();
            page = (String)session.getAttribute(SessionAttribute.CURRENT_PAGE);
        }
        String temp = TypeRe.redirect(page);
        return TypeRe.redirect(page);
    }


}
