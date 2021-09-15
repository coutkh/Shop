package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.service.BasketService;
import com.cout.shop.model.service.impl.BasketServiceImpl;
import com.cout.shop.util.TypeRe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConfirmAndBackCommand extends Command {
    private static final BasketService basketService = BasketServiceImpl.getInstance();
    public ConfirmAndBackCommand() {
        super.commandName = "CONFIRM_AND_BACK";
    }
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        int productId = Integer.parseInt(request.getParameter(RequestParameter.ID));
        int productCount = Integer.parseInt(request.getParameter(RequestParameter.COUNT));
        int productPrice = Integer.parseInt(request.getParameter(RequestParameter.PRICE));
        User user = (User) session.getAttribute(SessionAttribute.CURRENT_USER);

        try {
            boolean isAdd = basketService.addProductToBasket(productId, productCount, productPrice, user);
            if (isAdd) {
                page = TypeRe.redirect(PagePath.REDIRECT_PRODUCT_PAGE);
            } else {
                page = PagePath.ERROR404;
                //request.setAttribute(RequestAttribute.SIGN_UP_ERROR, "signup.incorrectSignup");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }
}
